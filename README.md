# Simple Network Sniffer

**Android Wi-Fi Network Scanner App**

Simple Network Sniffer is a lightweight Android app designed to scan the local Wi-Fi subnet for active and reachable devices. It demonstrates basic network discovery using Java and the Android SDK, and was originally built for educational purposes.

---

## 📈 What It Does

- Retrieves the device's current Wi-Fi IP address and subnet
- Iterates through the IP range (e.g., `192.168.1.1` to `192.168.1.254`)
- Attempts to connect to each IP using `InetAddress.isReachable`
- Logs reachable hosts and their canonical names to Logcat

---

## 👩‍💻 Use Cases

- Understand how subnet scanning works in Android apps
- Demonstrate `AsyncTask` and background threading
- Show how to use `WifiManager` and `ConnectivityManager` to access network info

---

## 🧰 Tech Stack

- **Java** (Android)
- **Android SDK (pre-AndroidX)**
- `AsyncTask` for background scanning
- `WifiManager`, `ConnectivityManager`, `InetAddress` for network operations

---

## ✨ Getting Started

### Prerequisites

- Android Studio (3.x+)
- Android SDK (API 23–28 recommended)
- Java 8

### Build & Run

1. Clone the repo:
   ```bash
   git clone https://github.com/didiergarcia/simple-network-sniffer.git
   cd simple-network-sniffer
   ```
2. Open in Android Studio.
3. Build and run on a real device (not emulator) connected to Wi-Fi.

---

## 🧪 How to Use

1. Launch the app
2. Tap the **"Sniff"** button
3. Watch Logcat for output showing reachable devices on your subnet

---

## 📊 Example Log Output

```
Host: router.local [router.local] (192.168.1.1) is reachable!
Host: macbook-pro [macbook-pro] (192.168.1.45) is reachable!
```

---

## ⚒ Future Improvements

- Use Kotlin and AndroidX libraries
- Add UI to display scan results
- Add hostname resolution timeout settings
- Implement concurrent pinging for faster results

---

## 📄 License

MIT © Didier Garcia\
Built for educational and diagnostic purposes.

