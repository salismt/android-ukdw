package com.bloonerd.androidukdw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListOnClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categories = new ArrayList<>();
        Category mvc = new Category("MVC", CategoryType.MVC);
        Category mvp = new Category("MVP", CategoryType.MVP);
        Category mvvm = new Category("MVVM", CategoryType.MVVM);

        categories.add(mvc);
        categories.add(mvp);
        categories.add(mvvm);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(categories, this));

    }

    @Override
    public void onClick(Category category) {
        switch (category.categoryType) {
            case CategoryType.MVC:
                Toast.makeText(this, category.name, Toast.LENGTH_SHORT).show();
                break;
            case CategoryType.MVP:
                Toast.makeText(this, category.name, Toast.LENGTH_SHORT).show();
                break;
            case CategoryType.MVVM:
                Toast.makeText(this, category.name, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    class MainAdapter extends RecyclerView.Adapter<ViewHolder> {

        List<Category> categories;
        private final ListOnClick onClickListener;

        public MainAdapter(List<Category> categories, ListOnClick onClickListener) {
            this.categories = categories;
            this.onClickListener = onClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.title.setText(categories.get(position).name);
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(categories.get(holder.getAdapterPosition()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_title);
        }
    }


}
