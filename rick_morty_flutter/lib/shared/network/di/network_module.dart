import 'package:dio/dio.dart';
import 'package:dio_logging_interceptor/dio_logging_interceptor.dart';
import 'package:injectable/injectable.dart';

@module
abstract class NetworkModule {
  @Named('BaseUrl')
  String get baseUrl => 'https://rickandmortyapi.com/api';

  @Named('LogginInterceptor')
  @lazySingleton
  Interceptor provideLoggingInterceptor() =>
      DioLoggingInterceptor(level: Level.body, compact: true);

  @lazySingleton
  Dio provideDio(@Named('BaseUrl') String baseUrl,
          @Named('LoggingInterceptor') Interceptor loggingInterceptor) =>
      Dio(BaseOptions(baseUrl: baseUrl))..interceptors.add(loggingInterceptor);
}
