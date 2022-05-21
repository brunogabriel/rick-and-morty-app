import 'package:flutter/material.dart';

enum RMTextStyle {
  // heading
  heading(TextStyle(fontSize: 40)),
  heading2(TextStyle(fontSize: 28)),
  heading3(TextStyle(fontSize: 22)),

  // body
  body20(TextStyle(fontSize: 20)),
  body17(TextStyle(fontSize: 17)),
  body15(TextStyle(fontSize: 15)),

  // large
  large(TextStyle(fontSize: 34)),

  // caption
  caption13(TextStyle(fontSize: 13)),
  caption11(TextStyle(fontSize: 11)),

  // headline
  headline15(TextStyle(fontSize: 15)),
  headling13(TextStyle(fontSize: 13)),
  headline11(TextStyle(fontSize: 11)),

  // tab
  tab(TextStyle(fontSize: 11));

  final TextStyle value;
  const RMTextStyle(this.value);
}
