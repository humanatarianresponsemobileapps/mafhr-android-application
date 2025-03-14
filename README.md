# MAFHR Android Application

This repository contains the source code for the MAFHR (Mobile Application for Humanitarian Response) Android application, designed to assist in efficient and effective humanitarian aid delivery.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

The Android application focuses on integrating RF-Sensing and drone video feeds for disaster management. Current capabilities include:  

1. **Login and Dashboard**  
   - Secure login system with reliable performance.  
   - User-friendly dashboard with a simple and clear interface.  

2. **Drone Feed Integration**  
   - Real-time retrieval and display of drone video streams.  
   - RF-Sensing overlays integrated into the video feed for enhanced situational awareness.  
     
---

### Future Directions
Planned improvements and features for the Android application include:

1. **Improved RF-Sensing/Federated Learning Models**  
- Integrate advanced RF-sensing models to improve disaster detection accuracy.  
- Leverage real-world disaster scenario data for validation.
- Improve fit function to train the model at a more rapid rate 

2. **Expanded Communication Protocols**  
- Incorporate MQTT-based communication for improved performance in low-bandwidth environments.  

3. **Cross-Platform Compatibility**  
- Extend functionality to iOS devices and web applications to increase accessibility.  

4. **Data Privacy and Security Enhancements**  
- Advanced encryption for federated model updates and disaster-related data.  
- Regular privacy audits to ensure compliance with global data protection regulations.
  
5. **Disaster Detection and Alerting**  
- Real-time detection of bushfires, earthquakes, and other disaster scenarios.
- Push notifications to users in affected areas based on RF-sensing data.

6. **User Login Options**  
- Separate login pages for general users and rescuers to tailor the app experience to their specific roles and access needs
- More advanced login and encryption methods (Google, Facebook, SSO)

7. **Heatmap Visualization**  
- A dynamic heatmap feature to identify and visualize danger zones in real-time, supporting informed decision-making and rescue prioritization.  

8. **Distress Button with GPS Integration**  
- A dedicated distress button allowing users to send SOS signals with their GPS coordinates to rescue teams for immediate assistance.  

9. **Live Chat and Alerts**  
- Real-time communication capabilities, including live chat between users and rescuers.  
- Push notifications for critical updates, alerts, and disaster-related information.  

10. **Incident Report Forms**  
- Structured reporting features to document incidents for better tracking and coordination of rescue efforts.  

## Installation

To set up the project locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/humanatarianresponsemobileapps/mafhr-android-application.git
   ```
2. **Open the project**:
   - Launch Android Studio.
   - Select "Open an existing project" and navigate to the cloned repository folder.
3. **Build the project**:
   - Ensure you have the latest version of Android Studio and the necessary SDKs installed.
   - Click on "Build" > "Make Project" to compile the application.

## Usage

1. **Run the application**:
   - Connect an Android device or start an emulator.
   - Click on "Run" > "Run 'app'" in Android Studio.
2. **Sign up or log in**:
   - Use the registration feature to create a new account or log in with existing credentials.
3. **Access features**:
   - Navigate through the app to utilize features like data collection, offline mode, and geolocation tracking.

## Contributing

We welcome contributions to enhance the MAFHR Android application. To contribute:

1. **Fork the repository**.
2. **Create a new branch**:
   ```bash
   git checkout -b feature/YourFeatureName
   ```
3. **Commit your changes**:
   ```bash
   git commit -m 'Add some feature'
   ```
4. **Push to the branch**:
   ```bash
   git push origin feature/YourFeatureName
   ```
5. **Open a Pull Request**.

Please ensure your code adheres to our coding standards and includes appropriate tests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact Anshu Madhikarmi at anshu.madhikarmi@deakin.edu.au

# Demo Video
https://youtu.be/VFGGvWpUbdI?si=xKYlVbV_98DIWsld

 
