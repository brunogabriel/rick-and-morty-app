import 'package:chucker_flutter/chucker_flutter.dart';
import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';

@module
abstract class NetworkModule {
  @Named("BaseUrl")
  String get baseUrl => "https://rickandmortyapi.com/api";

  @lazySingleton
  ChuckerDioInterceptor providesChuckerInterceptor() => ChuckerDioInterceptor();

  @lazySingleton
  Dio provideDio(
    @Named('BaseUrl') String baseUrl,
    ChuckerDioInterceptor chucker,
  ) {
    final Dio dio = Dio(BaseOptions(baseUrl: baseUrl));
    dio.interceptors.add(chucker);
    return dio;
  }
}
