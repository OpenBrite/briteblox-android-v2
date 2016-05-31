package com.openbrite.briteblox;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyApp {
	public static BluetoothLogService mLogService = null;
	public static BluetoothSocket btSocket = null;
	public static BluetoothAdapter mBluetoothAdapter = null;
    public static String[] messages;               // Set up enough strings to contain the desired # of messages
	public static HashMap<String, Integer> productColumns;
	public static HashMap<String, Integer> productRows;

	public static void initialize() {
		productColumns = new HashMap<>();
		productColumns.put("BriteBlox", 5);
		productColumns.put("Wearable", 13);
		productRows = new HashMap<>();
		productRows.put("BriteBlox", 7);
		productRows.put("Wearable", 8);
	}
}
