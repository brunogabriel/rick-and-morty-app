import 'package:flutter/material.dart';
import 'package:rick_morty_flutter/shared/design_system/app_theme.dart';
import 'package:rick_morty_flutter/shared/design_system/widgets/character_card.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: buildAppTheme(Brightness.dark),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children: [
          CharacterCard(
            onTap: () {},
            url: 'https://rickandmortyapi.com/api/character/avatar/156.jpeg',
          ),
          CharacterCard(
            onTap: () {},
            url: 'https://rickandmortyapi.com/api/character/avatar/156.jpeg',
          ),
          CharacterCard(
            onTap: () {},
            url: 'https://rickandmortyapi.com/api/character/avatar/156.jpeg',
          ),
          CharacterCard(
            onTap: () {},
            url: 'https://rickandmortyapi.com/api/character/avatar/156.jpeg',
          ),
          CharacterCard(
            onTap: () {},
            url: 'https://rickandmortyapi.com/api/character/avatar/156.jpeg',
          ),
        ],
      ),
    );
  }
}
