package hw.ms_hw10_201201356;

import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CUSTOMER_DATA = "CustomerKeyData";
    private static final int MAX_CUSTOMERS = 3;

    private Button button_start;
    private Button button_stop;
    private TextView textview_name;
    private TextView textview_phone;
    private TextView textview_area;

    private Customer[] customers;

    private CustomerHandler customerHandler;
    private boolean isRun;
    private int indexCurrentCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview_name = (TextView) findViewById(R.id.textview_name);
        textview_phone = (TextView) findViewById(R.id.textview_phone);
        textview_area = (TextView) findViewById(R.id.textview_area);

        button_start = (Button) findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRun) {
                    isRun = true;
                    new CustomerThread().start();
                }
            }
        });
        button_stop = (Button) findViewById(R.id.button_stop);
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRun = false;
            }
        });

        customers = new Customer[]{
                new Customer("김민호", "010-8768-3802", "대전시"),
                new Customer("홍길동", "010-1234-5678", "서울시"),
                new Customer("이철수", "010-5324-4123", "부산시")
        };
        setCustomerInfo(customers[0]);

        indexCurrentCustomer = 0;
        customerHandler = new CustomerHandler();
    }

    private void setCustomerInfo(Customer customer) {
        textview_name.setText(customer.name);
        textview_phone.setText(customer.phone);
        textview_area.setText(customer.area);
    }

    private class CustomerThread extends Thread {
        @Override
        public void run() {
            try {
                while (isRun) {
                    Bundle data = new Bundle();
                    data.putParcelable(KEY_CUSTOMER_DATA, customers[indexCurrentCustomer]);
                    Message msg = new Message();
                    msg.setData(data);

                    indexCurrentCustomer = ++indexCurrentCustomer % MAX_CUSTOMERS;
                    customerHandler.sendMessage(msg);
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class CustomerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Customer customer = msg.getData().getParcelable(KEY_CUSTOMER_DATA);
            setCustomerInfo(customer);
        }
    }

    private class Customer implements Parcelable {

        public String name;
        public String phone;
        public String area;

        public Customer(String name, String phone, String area) {
            this.name = name;
            this.phone = phone;
            this.area = area;
        }

        protected Customer(Parcel in) {
            name = in.readString();
            phone = in.readString();
            area = in.readString();
        }

        public final Creator<Customer> CREATOR = new Creator<Customer>() {
            @Override
            public Customer createFromParcel(Parcel in) {
                return new Customer(in);
            }

            @Override
            public Customer[] newArray(int size) {
                return new Customer[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(phone);
            parcel.writeString(area);
        }
    }
}