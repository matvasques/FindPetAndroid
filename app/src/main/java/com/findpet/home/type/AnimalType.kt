package com.findpet.home.type

enum class AnimalType(val value: String) {
    CAT("cat"),
    DOG("dog"),
    INVALID("invalid");

    companion object {
        fun fromValue(value: String?) = values().firstOrNull { it.value == value } ?: INVALID
    }
}