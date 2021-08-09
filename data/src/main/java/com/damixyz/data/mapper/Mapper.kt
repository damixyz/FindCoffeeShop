package com.damixyz.data.mapper

interface Mapper<T, R> {
    fun map(item: T): R
    fun mapList(items: List<T>): List<R> {
        return items.map { map(it) }
    }
}