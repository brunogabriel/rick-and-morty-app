import 'package:equatable/equatable.dart';
import 'package:flutter/foundation.dart';
// ignore: depend_on_referenced_packages
import 'package:json_annotation/json_annotation.dart';

part 'api_info_response.g.dart';

@immutable
@JsonSerializable()
class ApiInfoResponse extends Equatable {
  final int count;
  final int pages;
  final String? next;
  final String? previous;

  const ApiInfoResponse(this.count, this.pages, this.next, this.previous);

  @override
  List<Object?> get props => [count, pages, next, previous];

  factory ApiInfoResponse.fromJson(Map<String, dynamic> json) =>
      _$ApiInfoResponseFromJson(json);
}
