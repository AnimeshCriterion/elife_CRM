package com.elifeindia.crm.printersdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.elifeindia.crm.BuildConfig;
import com.elifeindia.crm.R;
import com.elifeindia.crm.contract.activities.PaymentReceiptContract;
import com.elifeindia.crm.model.PaymentReciept;
import com.elifeindia.crm.presenter.activities.PaymentReceiptPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.view.activities.HomeActivity;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
import com.printer.command.EscCommand;
import com.printer.command.LabelCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.elifeindia.crm.printersdk.Constant.MESSAGE_UPDATE_PARAMETER;

public class PrinterSettingsActivity extends AppCompatActivity {
    private int		id = 0;
    private ThreadPool threadPool;
    private static final int CONN_STATE_DISCONN = 0x007;
    private static final int	CONN_PRINTER		= 0x12;
    private static final int PRINTER_COMMAND_ERROR = 0x008;
    private static final int	CONN_MOST_DEVICES	= 0x11;
    Button txt_connected_device, txt_mac_address;
    TextView txt_select_device, txt_mac_add;
    RadioButton rb_3inch, rb_2inch, rb_yes, rb_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_settings);

        txt_connected_device = findViewById(R.id.txt_connected_device);
        txt_mac_address = findViewById(R.id.txt_mac_address);
        txt_select_device = findViewById(R.id.txt_select_device);
        txt_mac_add = findViewById(R.id.txt_mac_add);

        rb_2inch = findViewById(R.id.rb_2inch);
        rb_3inch = findViewById(R.id.rb_3inch);
        rb_yes = findViewById(R.id.rb_yes);
        rb_no = findViewById(R.id.rb_no);

//        txt_select_device.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult( new Intent(PrinterSettingsActivity.this, BluetoothDeviceList.class ), Constant.BLUETOOTH_REQUEST_CODE );
//
//            }
//        });


        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity( new Intent(PrinterSettingsActivity.this, HomeActivity.class ) );
                finish();
                Toast.makeText(PrinterSettingsActivity.this, "Settings saved successfully", Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.txt_select_device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult( new Intent( PrinterSettingsActivity.this, BluetoothDeviceList.class ), Constant.BLUETOOTH_REQUEST_CODE );
            }
        });
        findViewById(R.id.btn_dis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_mac_address.setVisibility(View.GONE);
                if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null ||
                        !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState() )
                {
                    Utils.toast( PrinterSettingsActivity.this, getString( R.string.str_cann_printer ) );
                    return;
                }
                mHandler.obtainMessage( CONN_STATE_DISCONN ).sendToTarget();
            }
        });

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage( Message msg )
        {
            switch ( msg.what )
            {
                case CONN_STATE_DISCONN:
                    if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null || !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState() )
                    {
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].closePort( id );

                        Toast.makeText(PrinterSettingsActivity.this, getString( R.string.str_disconnect_success ), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PRINTER_COMMAND_ERROR:

                    Toast.makeText(PrinterSettingsActivity.this, getString( R.string.str_choice_printer_command ), Toast.LENGTH_SHORT).show();
                    break;
                case CONN_PRINTER:
                    Toast.makeText(PrinterSettingsActivity.this, getString( R.string.str_cann_printer ), Toast.LENGTH_SHORT).show();

                    break;
                case MESSAGE_UPDATE_PARAMETER:
                    String strIp = msg.getData().getString( "Ip" );
                    String strPort = msg.getData().getString( "Port" );
                    new DeviceConnFactoryManager.Build()
                            .setConnMethod( DeviceConnFactoryManager.CONN_METHOD.WIFI )
                            .setIp( strIp )
                            .setId( id )
                            .setPort( Integer.parseInt( strPort ) )
                            .build();
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    } );
                    break;
                default:
                    new DeviceConnFactoryManager.Build()
                            .setConnMethod( DeviceConnFactoryManager.CONN_METHOD.WIFI )
                            .setIp( "192.168.2.227" )
                            .setId( id )
                            .setPort( 9100 )
                            .build();
                    threadPool.addTask( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    } );
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        super.onActivityResult( requestCode, resultCode, data );
        if ( resultCode == RESULT_OK )
        {
            switch ( requestCode )
            {

                case Constant.BLUETOOTH_REQUEST_CODE: {
                    closeport();
                    String macAddress = data.getStringExtra( BluetoothDeviceList.EXTRA_DEVICE_ADDRESS );
                        SharedPrefsData.putString(PrinterSettingsActivity.this, Constants.macAddress, macAddress, Constants.PREF_NAME);
                        SharedPrefsData.putInt(PrinterSettingsActivity.this, Constants.printerId, id, Constants.PREF_NAME);

                    new DeviceConnFactoryManager.Build().setId( id ).setConnMethod( DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH ).setMacAddress( macAddress ).build();

                    Log.d("TAG", "onActivityResult: 连接蓝牙"+id);
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    } );

                    break;
                }
//                /*USB连接*/
//                case Constant.USB_REQUEST_CODE: {
//                    closeport();
//                    /* 获取USB设备名 */
//                    usbName = data.getStringExtra( UsbDeviceList.USB_NAME );
//                    /* 通过USB设备名找到USB设备 */
//                    UsbDevice usbDevice = Utils.getUsbDeviceFromName( MainActivity.this, usbName );
//                    /* 判断USB设备是否有权限 */
//                    if ( usbManager.hasPermission( usbDevice ) )
//                    {
//                        usbConn( usbDevice );
//                    } else {        /* 请求权限 */
//                        mPermissionIntent = PendingIntent.getBroadcast( this, 0, new Intent( ACTION_USB_PERMISSION ), 0 );
//                        usbManager.requestPermission( usbDevice, mPermissionIntent );
//                    }
//                    break;
//                }
//                /*串口连接*/
//                case Constant.SERIALPORT_REQUEST_CODE:
//                    closeport();
//                    /* 获取波特率 */
//                    int baudrate = data.getIntExtra( Constant.SERIALPORTBAUDRATE, 0 );
//                    /* 获取串口号 */
//                    String path = data.getStringExtra( Constant.SERIALPORTPATH );
//
//                    if ( baudrate != 0 && !TextUtils.isEmpty( path ) )
//                    {
//                        /* 初始化DeviceConnFactoryManager */
//                        new DeviceConnFactoryManager.Build()
//                                /* 设置连接方式 */
//                                .setConnMethod( DeviceConnFactoryManager.CONN_METHOD.SERIAL_PORT )
//                                .setId( id )
//                                /* 设置波特率 */
//                                .setBaudrate( baudrate )
//                                /* 设置串口号 */
//                                .setSerialPort( path )
//                                .build();
//                        /* 打开端口 */
//                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//                    }
//                    break;
                case CONN_MOST_DEVICES:
                    id = data.getIntExtra( "id", -1 );
                    if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null &&
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState() )
                    {
                        Toast.makeText(this, getString( R.string.str_conn_state_connected ) + "\n" + getConnDeviceInfo(), Toast.LENGTH_SHORT).show();
                        txt_connected_device.setText("Printer is connected");

                        //tvConnState.setText( g );
                    } else {
                        Toast.makeText(this, getString( R.string.str_conn_state_disconnect ), Toast.LENGTH_SHORT).show();
                        txt_connected_device.setText("Printer is not connected");
                        //tvConnState.setText( getString( R.string.str_conn_state_disconnect ) );
                    }
                    break;
                default:
                    break;
            }
        }
    }
    private void
    closeport()
    {
        if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null &&DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort != null )
        {
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].reader.cancel();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort.closePort();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort = null;
        }
    }

    private String getConnDeviceInfo()
    {
        String				str				= "";
        DeviceConnFactoryManager	deviceConnFactoryManager	= DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id];
        if ( deviceConnFactoryManager != null
                && deviceConnFactoryManager.getConnState() )
        {
            if ( "USB".equals( deviceConnFactoryManager.getConnMethod().toString() ) )
            {
                str	+= "USB\n";
                str	+= "USB Name: " + deviceConnFactoryManager.usbDevice().getDeviceName();
            } else if ( "WIFI".equals( deviceConnFactoryManager.getConnMethod().toString() ) )
            {
                str	+= "WIFI\n";
                str	+= "IP: " + deviceConnFactoryManager.getIp() + "\t";
                str	+= "Port: " + deviceConnFactoryManager.getPort();
            } else if ( "BLUETOOTH".equals( deviceConnFactoryManager.getConnMethod().toString() ) )
            {
                str	+= "BLUETOOTH\n";
                str	+= "MacAddress: " + deviceConnFactoryManager.getMacAddress();
                txt_mac_address.setVisibility(View.VISIBLE);
                txt_mac_add.setText(deviceConnFactoryManager.getMacAddress());
                txt_mac_address.setText("Mac Add : "+deviceConnFactoryManager.getMacAddress());
            } else if ( "SERIAL_PORT".equals( deviceConnFactoryManager.getConnMethod().toString() ) )
            {
                str	+= "SERIAL_PORT\n";
                str	+= "Path: " + deviceConnFactoryManager.getSerialPortPath() + "\t";
                str	+= "Baudrate: " + deviceConnFactoryManager.getBaudrate();
            }
        }
        return(str);
    }
}