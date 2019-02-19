package nl.rodenboch.rodenborchagenda;

import android.content.DialogInterface;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import nl.rodenboch.rodenborchagenda.models.NewsResource;
import nl.rodenboch.rodenborchagenda.services.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.text.HtmlCompat.FROM_HTML_MODE_LEGACY;

public class AgendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        setSupportActionBar ((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled (true);

        mListView = findViewById(R.id.listview);

        Call<ArrayList<NewsResource>> call = RestService.getEndPoint().getNews();
        call.enqueue(new Callback<ArrayList<NewsResource>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsResource>> call, Response<ArrayList<NewsResource>> response) {

                mAdapter = new ArrayAdapter(AgendaActivity.this,
                        android.R.layout.simple_list_item_1, response.body());
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<NewsResource>> call, Throwable t) {

            }
        });

        mListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        NewsResource newsItem = (NewsResource) mAdapter.getItem(i);

        AlertDialog alertDialog = new AlertDialog.Builder(AgendaActivity.this).create();
        alertDialog.setTitle(newsItem.getTitle());
        alertDialog.setMessage(HtmlCompat.fromHtml(newsItem.getSummary(), FROM_HTML_MODE_LEGACY));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
