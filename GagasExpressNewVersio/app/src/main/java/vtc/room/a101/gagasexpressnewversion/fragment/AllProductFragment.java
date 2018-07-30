package vtc.room.a101.gagasexpressnewversion.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vtc.room.a101.gagasexpressnewversion.R;
import vtc.room.a101.gagasexpressnewversion.adapter.AllProductAdapter;
import vtc.room.a101.gagasexpressnewversion.module.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductFragment extends Fragment {

    public AllProductAdapter allProductAdapter;

    public AllProductFragment() {
        // Required empty public constructor
    }


    private static List<Product> list;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_product, container, false);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);
        allProductAdapter = new AllProductAdapter(getContext(), list, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(allProductAdapter);
        return view;
    }

    public static List<Product> getList() {
        return list;
    }

    public void updateList(final AllProductAdapter allProductAdapter) {
        recyclerView.setAdapter(allProductAdapter);
    }

    public static void setList(List<Product> list) {
        AllProductFragment.list = list;
    }

}
