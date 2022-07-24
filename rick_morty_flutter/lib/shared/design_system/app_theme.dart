import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

ThemeData buildAppTheme(brightness) {
  final baseTheme = ThemeData(
    brightness: brightness,
    primarySwatch: Colors.blue,
  );

  return baseTheme.copyWith(
    textTheme: GoogleFonts.notoSansTextTheme(baseTheme.textTheme),
  );
}
