# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**greetGet**](DefaultApi.md#greetGet) | **GET** /greet |  |
| [**greetGreetingPut**](DefaultApi.md#greetGreetingPut) | **PUT** /greet/greeting |  |
| [**greetNameGet**](DefaultApi.md#greetNameGet) | **GET** /greet/{name} |  |
| [**simpleGreetGet**](DefaultApi.md#simpleGreetGet) | **GET** /simple-greet |  |
| [**simpleGreetNameGet**](DefaultApi.md#simpleGreetNameGet) | **GET** /simple-greet/{name} |  |



## greetGet

> Message greetGet()



### Parameters

This endpoint does not need any parameter.

### Return type

[**Message**](Message.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## greetGreetingPut

> void greetGreetingPut(body)



### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **body** | **Object**|  | |

### Return type

[**void**](Void.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Greeting updated |  -  |
| **400** | JSON did not contain setting for &#39;greeting&#39; |  -  |


## greetNameGet

> Message greetNameGet(name)



### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **name** | **String**|  | |

### Return type

[**Message**](Message.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## simpleGreetGet

> Message simpleGreetGet()



### Parameters

This endpoint does not need any parameter.

### Return type

[**Message**](Message.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## simpleGreetNameGet

> Message simpleGreetNameGet(name)



### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **name** | **String**|  | |

### Return type

[**Message**](Message.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

