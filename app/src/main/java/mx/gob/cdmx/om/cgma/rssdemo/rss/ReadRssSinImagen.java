package mx.gob.cdmx.om.cgma.rssdemo.rss;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mx.gob.cdmx.om.cgma.rssdemo.FeedAdapter;
import mx.gob.cdmx.om.cgma.rssdemo.FeedItem;

/**
 * Created by victor on 22/03/17.
 */

public class ReadRssSinImagen extends AsyncTask<Void,Void,Void> {
    Context context;
    ProgressDialog progressDialog;
    String xml;
    URL url;
    List<FeedItem> feedItems;
    RecyclerView recyclerView;

    public ReadRssSinImagen(Context context, RecyclerView recyclerView,String url) {
        this.xml = url;
        this.recyclerView = recyclerView;
        this.context = context;
        Log.d("ROOT","ReadRssAnime Cargando..");
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando...");
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(getData());
        //getData();
        return null;
    }

    private void ProcessXml(Document data) {
        if (data != null) {
            Log.d("ROOT", "ReadRssAnime ProcessXml..");
            Log.d("ROOT", data.getDocumentElement().getNodeName());

            feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            Log.d("ROOT", items.getLength()+"");

            for (int i=0; i<items.getLength();i++) {
                Node nodeChild = items.item(i);

                if (nodeChild.getNodeName().equalsIgnoreCase("item")){

                    FeedItem item = new FeedItem();
                    NodeList itemChilds = nodeChild.getChildNodes();

                    for (int j = 0; j < itemChilds.getLength(); j++) {

                        Node current = itemChilds.item(j);

                        //Log.d("ROOT", current.getNodeName());
                        if (current.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        }
                        item.setUrlImage("");
                    }


                    feedItems.add(item);
                    Log.d("Item",item.getTitle());
                    Log.d("description",item.getDescription());
                    Log.d("pubDate",item.getPubDate());
                    Log.d("link",item.getLink());
                }
            }
        }
    }

    public Document getData(){
        try{

            url = new URL(xml);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(inputStream);
            Log.d("ROOT",xml);
            return xmlDocument;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();

        if (feedItems != null){
            FeedAdapter feedAdapter = new FeedAdapter(feedItems,context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //recyclerView.addItemDecoration(new VerticalSpace(30));
            recyclerView.setAdapter(feedAdapter);
        }

    }
}
