package com.vless.data.content.model

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name="product_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    @Column(name="label")
    @NotEmpty
    var label:String = ""

    @Column(name="parent")
    @NotNull
    var parent:Long=0

    @Transient
    var children:List<ProductType> = ArrayList()
}