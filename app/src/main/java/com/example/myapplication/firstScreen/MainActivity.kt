package com.example.myapplication.firstScreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.compagnionObjectClass.Lists
import com.example.myapplication.sqlite.DB_news
import com.example.myapplication.sqlite.dbNews
import com.example.myapplication.webService.Net
import com.example.myapplication.webService.NewRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news.*
import org.jetbrains.anko.toast
import android.support.v7.widget.SearchView
import com.example.myapplication.secondScreen.SecondActivity
import com.example.myapplication.secondScreen.SecondScreenFragment
import org.jetbrains.anko.startActivityForResult

class MainActivity : AppCompatActivity(), FirstScreenFragment.Listener  {

    private val newsRequest     = NewRequest()
    private val net             = Net()
    private val dB_news         = DB_news()

    //lateinit var popupWindow: PopupWindow

    override fun onNewsSelection(id: Long) {
        val fragDetail = fragmentManager.findFragmentById(R.id.frag_news_detail) as SecondScreenFragment?
        if (fragDetail != null){
            fragDetail.showDetail(id)
        }else{
            startActivityForResult<SecondActivity>(1, SecondActivity.EXTRA_NEWS_ID to id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (dB_news.isExist(dbNews))
            dB_news.delete(dbNews)
        if (net.connect(this)) {
            newsRequest.run(dbNews, "https://airweb-demo.airweb.fr/psg/psg.json")
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choiceType()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //val fragList = fragmentManager.findFragmentById(R.id.list_news_fragment) as FirstScreenFragment
        //fragList.loadListNews()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.first_screen, menu)

        //search bar
        val searchItem = menu!!.findItem(R.id.search)
        if (searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(newText: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()){
                        Lists.searchNews.clear()
                        val search = newText.toLowerCase()
                        Lists.listNews.forEach {
                            if (it.title.toLowerCase().contains(search)){
                                Lists.searchNews.add(it)
                            }
                        }
                        rv_list_news.adapter.notifyDataSetChanged()
                    }else{
                        Lists.searchNews.clear()
                        Lists.searchNews.addAll(Lists.listNews)
                        rv_list_news.adapter.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    //spinner to choice type
    private fun choiceType(){
        val adapter = ArrayAdapter.createFromResource(this, R.array.type_news, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = adapter.getItem(position)

                val fragTypeSelect = fragmentManager.findFragmentById(R.id.list_news_fragment) as FirstScreenFragment
                // all type
                if (item.toString() == "Tous") fragTypeSelect.loadListNews()
                //other type (hot, news, actualité)
                else if (item.toString() == "hot" || item.toString() == "news" || item.toString() == "actualité")
                    fragTypeSelect.loadListNewsWithType(item.toString())
                //other type (Sort by date)
                else if (item.toString() == "trier par: date")
                    fragTypeSelect.loadListNewsSortByDate()
                //other type (Sort by title)
                else if (item.toString() == "trier par: titre")
                    fragTypeSelect.loadListNewsSortByTitle()
            }

        }
    }

    /*
    //open window of popup (dialog)
    private fun popupView() {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_view, null)
        // Initialize a new instance of popup window
        popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.MATCH_PARENT, // width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )
        //Set an elevation for the popup window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            popupWindow.elevation = 10.0F
        }
        // If API Level 23 or higher then execute the code
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Create a new slide animation for popup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn
        }// Set a dismiss listener for popup window
        popupWindow.setOnDismissListener {
            Toast.makeText(applicationContext,"Popup closed", Toast.LENGTH_SHORT).show()
        }
        // Finally, show the popup window on app
        TransitionManager.beginDelayedTransition(firstScreen)
        popupWindow.showAtLocation(
            firstScreen, // Location to display popup window
            Gravity.CENTER, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )

    }*/

}
