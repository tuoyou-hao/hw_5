package com.jnu.hw_5;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {

    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private ArrayList<Book> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_main);

        RecyclerView recyclerViewMain=findViewById(R.id.recycle_view_book);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        bookList = new ArrayList<>();

        //设置三本书
        Book book1=new Book();
        book1.bookname="软件项目管理案例教程（第4版）";
        book1.bookID=R.drawable.book_2;

        Book book2=new Book();
        book2.bookname="创新工程实践";
        book2.bookID=R.drawable.book_no_name;

        Book book3=new Book();
        book3.bookname="软件项目管理案例教程（第4版）";
        book3.bookID=R.drawable.book_1;

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        MainRecycleViewAdapter mainRecycleViewAdapter=new MainRecycleViewAdapter(bookList);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);



    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        System.out.println(item.getItemId());
        switch(item.getItemId())
        {
            case MENU_ID_ADD:
                Toast.makeText(this,"book add" +item.getOrder()+" clicked!",Toast.LENGTH_LONG)
                        .show();
                break;
            case MENU_ID_UPDATE:
                Toast.makeText(this,"book update " +item.getOrder()+" clicked!",Toast.LENGTH_LONG)
                        .show();
                break;
            case MENU_ID_DELETE:
                Toast.makeText(this,"book delete " +item.getOrder()+" clicked!",Toast.LENGTH_LONG)
                        .show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //书本类
    public class Book{
        private String bookname;
        private int bookID;

        public int getCoverResourceId(){
            return this.bookID;
        }

        public String getTitle(){
            return this.bookname;
        }
    }

    //获取书本列表方法
    public ArrayList<Book> getListBooks(){
        return this.bookList;
    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private final ArrayList<Book> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            private final TextView textView;
            private final ImageView imageViewImage;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                imageViewImage = view.findViewById(R.id.image_view_book_cover);
                textView = view.findViewById(R.id.text_view_book_title);

                view.setOnCreateContextMenuListener(this);
            }

            public TextView getTextView() {
                return textView;
            }
            public ImageView getImageViewImage() {
                return imageViewImage;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,MENU_ID_ADD,getAdapterPosition(),"Add "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_UPDATE,getAdapterPosition(),"Update "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_DELETE,getAdapterPosition(),"Delete "+getAdapterPosition());
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public MainRecycleViewAdapter(ArrayList<Book> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.booklist_main, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView().setText(localDataSet.get(position).getTitle());
            viewHolder.getImageViewImage().setImageResource(localDataSet.get(position).getCoverResourceId());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}