import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:rick_and_morty_flutter/character/presentation/page/character_page.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/colors.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/styles.dart';

class MainPage extends StatefulWidget {
  const MainPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  int _selectedIndex = 0;

  static const List<Widget> _widgetOptions = <Widget>[
    CharacterPage(),
    Text(
      'Index 1: Location',
    ),
    Text(
      'Index 2: Episode',
    ),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  BottomNavigationBarItem _buildNavItem(String image, String title) =>
      BottomNavigationBarItem(
        icon: Image.asset(
          image,
          height: 28,
          color: RMColor.gray1.value,
        ),
        activeIcon: Image.asset(
          image,
          height: 28,
          color: RMColor.accent.value,
        ),
        label: title,
      );

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          elevation: 0,
          shadowColor: RMColor.white.value,
          backgroundColor: RMColor.white.value,
          surfaceTintColor: RMColor.white.value,
          foregroundColor: RMColor.white.value,
          systemOverlayStyle: SystemUiOverlayStyle(
            statusBarColor: RMColor.white.value,
            statusBarIconBrightness: Brightness.dark, // Android (dark icons)
            statusBarBrightness: Brightness.light, // iOS (dark icons)
          ),
        ),
        body: _widgetOptions.elementAt(_selectedIndex),
        bottomNavigationBar: BottomNavigationBar(
          items: [
            _buildNavItem('icons/ghost.png', 'Character'),
            _buildNavItem('icons/planet.png', 'Location'),
            _buildNavItem('icons/tv.png', 'Episode'),
          ],
          currentIndex: _selectedIndex,
          selectedItemColor: RMColor.accent.value,
          unselectedItemColor: RMColor.gray1.value,
          onTap: _onItemTapped,
          selectedLabelStyle: RMTextStyle.tab.value,
          unselectedLabelStyle: RMTextStyle.tab.value,
          //fixedColor: RMColor.gray.value,
        ),
      ),
    );
  }
}
