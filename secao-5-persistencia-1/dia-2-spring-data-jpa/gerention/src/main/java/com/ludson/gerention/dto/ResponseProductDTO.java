package com.ludson.gerention.dto;

public record ResponseProductDTO<T>(String message, T data) {

}