    package com.logicalsolutions.gruhaved.db;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.widget.Toast;

    import androidx.annotation.Nullable;

    public class DatabaseHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "Gruhaved.db";
        private static final int DATABASE_VERSION = 2;
        private static final String TABLE_Cart = "Cart_Master";
        private static final String TABLE_Address = "Address_Master";
        private static final String TABLE_Product = "Product_Maser";

        private static final String TABLE_OM = "Order_Master";
        private static final String TABLE_OD = "Order_Details";

        private static final String AddressID = "Address_ID";

        private static final String AddressFullName = "FullName";
        private static final String AddressCONTACT = "ContactNo";
        private static final String AddressFLAT_NO = "FlatNo";

        private static final String AddressWING = "Wing";
        private static final String AddressSOCIETY = "Society";
        private static final String AddressCITY = "City";

        private static final String AddressPinCode = "Pincode";

//        Product Table
        private static final String ProductID = "ProductID";
        private static final String ProductName ="ProductName";
        private static final String PriceperUnit="PricePerUnit";
        private static final String ProductUnit="ProductUnit";
        private static final String ProductImage="ProductImage";

// Order Table
        private static final String OrderID = "Order_ID";
        private static final String OrderDATE = "OrderDate";
        private static final String Orderstatus = "OrderStatus";
        private static final String Qty = "Qty";
        private static final String Amount = "Amount";
        private static final String OrderQty = "QtyTotal";
        private static final String OrderTotal = "AmountTotal";




        private final Context context;

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_OM + "("
                    + OrderID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + AddressID + " INTEGER,"
                    + OrderDATE + " TEXT,"
                    + Orderstatus + " TEXT,"
                    + OrderQty + " INTEGER,"
                    + OrderTotal + " TEXT)";
            db.execSQL(CREATE_ORDERS_TABLE);


            String CREATE_ORDER_DETAIL_TABLE = "CREATE TABLE " + TABLE_OD + "("
                    + OrderID + " INTEGER,"
                    + ProductID + " INTEGER,"
                    + Qty + " INTEGER,"
                    + Amount + " TEXT)";
            db.execSQL(CREATE_ORDER_DETAIL_TABLE);

            String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_Cart + "("
                    + ProductID + " INTEGER,"
                    + Qty + " INTEGER)";
            db.execSQL(CREATE_CART_TABLE);


            String CREATE_Address_TABLE = "CREATE TABLE " + TABLE_Address + "("
                    + AddressID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + AddressFullName + " TEXT, "
                    + AddressCONTACT + " TEXT,"
                    + AddressFLAT_NO + " TEXT,"
                    + AddressWING + " TEXT,"
                    + AddressSOCIETY + " TEXT,"
                    + AddressCITY + " TEXT,"
                    + AddressPinCode + " INTEGER );";
            db.execSQL(CREATE_Address_TABLE);
            String CREATE_Product_TABLE = "CREATE TABLE " + TABLE_Product + "("
                    + ProductID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ProductImage + " TEXT,"
                    + ProductName + " TEXT,"
                    + PriceperUnit + " INTEGER,"
                    + ProductUnit + " TEXT);";

            db.execSQL(CREATE_Product_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_OM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_OD);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Cart);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Address);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Product);
            onCreate(db);
        }

        public boolean insertOrder(String orderDate, Integer addressId, String orderStatus, Integer orderQty, String orderTotal) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(OrderDATE, orderDate);
            contentValues.put(AddressID, addressId);
            contentValues.put(Orderstatus, orderStatus);
            contentValues.put(OrderQty, orderQty);
            contentValues.put(OrderTotal, orderTotal);
            long result = db.insert(TABLE_OM, null, contentValues);
            return result != -1;
        }

        public void InsertAddress(String fullname, String contact, String flatno, String wing, String society, String city, Integer pincode) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(AddressFullName, fullname);
            contentValues.put(AddressCONTACT, contact);
            contentValues.put(AddressFLAT_NO, flatno);
            contentValues.put(AddressWING, wing);
            contentValues.put(AddressSOCIETY, society);
            contentValues.put(AddressCITY, city);
            contentValues.put(AddressPinCode, pincode);
            long value = db.insert(TABLE_Address, null, contentValues);
            Toast.makeText(context, "Address saved" + value, Toast.LENGTH_SHORT).show();

        }
        public boolean insertProduct(String pName, Integer priceUnit, String productUnit) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ProductName, pName);
            contentValues.put(PriceperUnit, priceUnit);
            contentValues.put(ProductUnit, productUnit);
           // contentValues.put("ImagePath", ProductImage);  // Store image path

            long result = db.insert(TABLE_Product, null, contentValues);
            return result != -1;
        }
        public Cursor getproductdata() {
            String query = "SELECT * FROM " + TABLE_Product;
            SQLiteDatabase Db = this.getReadableDatabase();
            Cursor cursor = Db.rawQuery(query, null);
            return cursor;
        }



        public Cursor getaddresslistdata() {
            String query = "SELECT * FROM " + TABLE_Address;
            SQLiteDatabase Db = this.getReadableDatabase();
            Cursor cursor = Db.rawQuery(query, null);
            return cursor;
        }
        public void update(Integer AddressID,String fullname, String contact, String flatno, String wing,
                           String society, String city, Integer pincode){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(AddressFullName, fullname);
            cv.put(AddressCONTACT, contact);
            cv.put(AddressFLAT_NO, flatno);
            cv.put(AddressWING, wing);
            cv.put(AddressSOCIETY, society);
            cv.put(AddressCITY, city);
            cv.put(AddressPinCode, pincode);
            long result =db.update(TABLE_Address,cv,"Address_ID="+ AddressID,null);
            Toast.makeText(context, "Address Updated", Toast.LENGTH_SHORT).show();

        }


        public void update(Integer productID, String productName, Integer priceUnit, String productUnit) {
            SQLiteDatabase db= this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(ProductName,productName);
            cv.put(PriceperUnit, priceUnit);
            cv.put(ProductUnit, productUnit);
            //cv.put(ProductImage,productImage);
            long result = db.update(TABLE_Product,cv,"ProductID"+ productID,null);
            Toast.makeText(context, "Product Updated", Toast.LENGTH_SHORT).show();
        }
    }

