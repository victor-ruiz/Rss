package mx.gob.cdmx.om.cgma.rssdemo.rss_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.gob.cdmx.om.cgma.rssdemo.R;
import mx.gob.cdmx.om.cgma.rssdemo.rss.ReadRss;
import mx.gob.cdmx.om.cgma.rssdemo.rss.ReadRssSinImagen;

/**
 * Created by victor on 22/03/17.
 */

public class AnimeFragment extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.layout_fragment_import,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_notas);

        String xml = "http://www.crunchyroll.com/newsrss?lang=esES";
        ReadRssSinImagen readRss = new ReadRssSinImagen(AnimeFragment.this.getActivity(),recyclerView,xml);
        readRss.execute();

        return rootView;
    }
}
