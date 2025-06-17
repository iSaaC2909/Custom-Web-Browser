# Java Web Browser

A feature-rich web browser implementation built in Java, providing a modern and user-friendly browsing experience with essential functionality for everyday web navigation.

## 🚀 Features

- **Modern User Interface**: Clean and intuitive GUI built with Java Swing
- **Navigation Controls**: 
  - Address bar for direct URL input
  - Back and forward navigation
  - Refresh functionality
  - Go button for quick navigation
- **Bookmark Management**: Save and organize your favorite websites
- **History Tracking**: Keep track of your browsing history
- **Security Features**: Basic firewall protection
- **File Management**: Efficient handling of browser data and settings

## 🛠️ Technical Architecture

### Core Components

1. **GUIManager (GUIManager.java)**
   - Manages the main user interface
   - Handles window creation and layout
   - Coordinates between different UI components

2. **Window Management (Window.java)**
   - Controls browser window behavior
   - Manages window sizing and positioning
   - Handles window events and interactions

3. **Button Handler (BtnHandler.java)**
   - Processes all button-related events
   - Manages navigation actions
   - Handles user interactions with browser controls

4. **File Handling (FileHandling.java)**
   - Manages data persistence
   - Handles bookmark storage
   - Manages history records
   - Processes configuration files

5. **Menu System (Menu.java)**
   - Provides dropdown menu functionality
   - Manages browser settings
   - Handles user preferences

6. **Mouse Listener (MouseListner.java)**
   - Processes mouse events
   - Handles click interactions
   - Manages mouse-based navigation

### Data Storage

The browser uses several text files for data persistence:
- `fav.txt`: Stores bookmarks
- `history.txt`: Maintains browsing history
- `home.txt`: Stores homepage settings
- `start.txt`: Contains startup configuration
- `firewall.txt`: Stores security settings
- `date.txt`: Manages date-related preferences

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Ant (for building the project)

### Building the Project

1. Clone the repository
2. Navigate to the project directory
3. Run the build command:
   ```bash
   ant build
   ```

### Running the Browser

Execute the following command:
```bash
java -jar WebBrowser.jar
```

## 📁 Project Structure

```
Web-Browser/
├── src/
│   ├── Driver.java           # Main entry point
│   ├── GUIManager.java       # GUI management
│   ├── Window.java          # Window handling
│   ├── BtnHandler.java      # Button event handling
│   ├── FileHandling.java    # File operations
│   ├── Menu.java            # Menu system
│   └── MouseListner.java    # Mouse event handling
├── resources/
│   ├── *.png                # UI assets
│   └── *.txt                # Configuration files
└── build.xml                # Ant build configuration
```

## 🔧 Configuration

The browser can be configured through various text files:
- Set your homepage in `home.txt`
- Configure startup behavior in `start.txt`
- Manage bookmarks in `fav.txt`
- View browsing history in `history.txt`
- Configure security settings in `firewall.txt`

## 🛠️ Development

### Adding New Features

1. Create new Java classes in the src directory
2. Update the build.xml file if necessary
3. Implement the feature following the existing architecture
4. Update the GUI components in GUIManager.java

### Building from Source

1. Ensure all dependencies are installed
2. Run `ant build` to compile the project
3. The compiled JAR will be created in the dist directory

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## 📧 Support

For support, please open an issue in the repository or contact the development team.

---

*This README was last updated on March 2024* 