package com.example.domain.builder

import com.example.domain.table.model.Table

class TableBuilder {
	private var tableName: String = "test_table"
	private var pk: String = "pk_1"
	private var batchSize: Int = 100

	fun withTableName(tableName: String) = apply { this.tableName = tableName }

	fun withPk(pk: String) = apply { this.pk = pk }

	fun withBatchSize(batchSize: Int) = apply { this.batchSize = batchSize }

	fun build() =
		Table(
			tableName = tableName,
			pk = pk,
			batchSize = batchSize,
		)
}

fun table(block: TableBuilder.() -> Unit = {}) = TableBuilder().apply(block).build()
