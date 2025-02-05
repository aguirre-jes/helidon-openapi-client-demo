/**
 * Generated API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package io.helidon.examples.openapigen.mp.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;




public class Message  {
  
  private String message;

  private String greeting;

 /**
   * Get message
   * @return message
  **/
  public String getMessage() {
    return message;
  }

  /**
    * Set message
  **/
  public void setMessage(String message) {
    this.message = message;
  }

  public Message message(String message) {
    this.message = message;
    return this;
  }

 /**
   * Get greeting
   * @return greeting
  **/
  public String getGreeting() {
    return greeting;
  }

  /**
    * Set greeting
  **/
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  public Message greeting(String greeting) {
    this.greeting = greeting;
    return this;
  }


  /**
    * Create a string representation of this pojo.
  **/
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    greeting: ").append(toIndentedString(greeting)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

