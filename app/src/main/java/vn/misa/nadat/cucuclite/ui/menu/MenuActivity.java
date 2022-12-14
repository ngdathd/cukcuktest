package vn.misa.nadat.cucuclite.ui.menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import vn.misa.nadat.cucuclite.R;
import vn.misa.nadat.cucuclite.items.ItemDish;
import vn.misa.nadat.cucuclite.items.ItemMenu;
import vn.misa.nadat.cucuclite.ui.dish.AddDishActivity;
import vn.misa.nadat.cucuclite.ui.dish.EditDishActivity;

public class MenuActivity extends AppCompatActivity implements DishAdapter.IClickItemDish {
    private List<ItemDish> itemDishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initRcMenu();
        initRcDish();
    }

    private void initRcDish() {
        RecyclerView recyclerView = findViewById(R.id.rcv_main_content);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        itemDishes = new ArrayList<>();
        ItemDish.Builder builder = new ItemDish.Builder();
        builder.setImgColor(Color.parseColor("#0d47a1"))
                .setImgIcon("_1_banh_cuon.png")
                .setName("aaaaaa")
                .setPrice("12344")
                .setSale(false);
        itemDishes.add(builder.build());
        DishAdapter dishAdapter = new DishAdapter(this, itemDishes, this);
        recyclerView.setAdapter(dishAdapter);
    }

    private void initRcMenu() {
        RecyclerView recyclerView = findViewById(R.id.rcv_navigation);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<ItemMenu> itemMenus = new ArrayList<>();
        itemMenus.add(new ItemMenu(R.drawable.ic_sale, "B??n h??ng"));
        itemMenus.add(new ItemMenu(R.drawable.ic_menu, "Th???c ????n"));
        itemMenus.add(new ItemMenu(R.drawable.ic_chart, "B??o c??o"));
        itemMenus.add(new ItemMenu(R.drawable.ic_sync, "?????ng b??? d??? li???u"));
        itemMenus.add(new ItemMenu(R.drawable.ic_settings_gray, "Thi???t l???p"));
        itemMenus.add(new ItemMenu(R.drawable.ic_notifications, "Th??ng b??o"));
        itemMenus.add(new ItemMenu(R.drawable.ic_share, "Gi???i thi???u cho b???n"));
        itemMenus.add(new ItemMenu(R.drawable.ic_rate, "????nh gi?? ???ng d???ng"));
        itemMenus.add(new ItemMenu(R.drawable.ic_info, "Th??ng tin s???n ph???m"));
        MenuAdapter menuAdapter = new MenuAdapter(itemMenus);
        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add: {
                startActivity(new Intent(MenuActivity.this, AddDishActivity.class));
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void clickDishItem(int i) {
        Intent intent = new Intent(this, EditDishActivity.class);
        intent.putExtra("id", i);
        startActivity(intent);
    }
}
