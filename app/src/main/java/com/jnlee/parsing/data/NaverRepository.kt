package com.jnlee.parsing.data

import com.jnlee.parsing.domain.Review
import com.jnlee.parsing.presentation.LLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class NaverRepository {

    private val _reviewList = MutableStateFlow<MutableList<Review>>(mutableListOf())
    val reviewList: StateFlow<MutableList<Review>> get() = _reviewList

    val link =
        "https://m.place.naver.com/hospital/12949526/review/visitor?entry=ple"

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val doc: Document = Jsoup.connect(link).get()

                val reviews: Elements = doc.select("ul[class=eCPGL]").select("li")
                for (ele in reviews) {
                    val reviewerInfo = ele.select("div[class=Lia3P]").select("a")
                    val image = reviewerInfo[0].select(".place_thumb img").attr("src")
                    val reviewerName = reviewerInfo[1].select("div[class=sBWyy]").text()
                    val reviewContent =
                        ele.select("div[class=ZZ4OK IwhtZ]").select("a").select("span").text()

                    //5.29.월, 6.1.목
                    val reviewDate =
                        ele.select("div[class=sb8UA]").select("span").select(".P1zUJ time").text()

                    val review = Review(
                        name = reviewerName,
                        image = image,
                        content = reviewContent,
                        date = reviewDate
                    )
                    _reviewList.value.add(review)
                    //TODO : emit
                }
                LLog.debug(_reviewList.value)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
}