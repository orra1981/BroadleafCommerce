/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.broadleafcommerce.core.offer.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.order.domain.OrderItemImpl;
import org.broadleafcommerce.money.Money;
import org.broadleafcommerce.presentation.AdminPresentation;
import org.hibernate.annotations.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_ORDER_ITEM_ADJUSTMENT")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
public class OrderItemAdjustmentImpl implements OrderItemAdjustment {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "OrderItemAdjustmentId")
    @GenericGenerator(
        name="OrderItemAdjustmentId",
        strategy="org.broadleafcommerce.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="table_name", value="SEQUENCE_GENERATOR"),
            @Parameter(name="segment_column_name", value="ID_NAME"),
            @Parameter(name="value_column_name", value="ID_VAL"),
            @Parameter(name="segment_value", value="OrderItemAdjustmentImpl"),
            @Parameter(name="increment_size", value="50"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.core.offer.domain.OrderItemAdjustmentImpl")
        }
    )
    @Column(name = "ORDER_ITEM_ADJUSTMENT_ID")
    protected Long id;

    @ManyToOne(targetEntity = OrderItemImpl.class, optional=false)
    @JoinColumn(name = "ORDER_ITEM_ID")
    @Index(name="OIADJUST_ITEM_INDEX", columnNames={"ORDER_ITEM_ID"})
    protected OrderItem orderItem;

    @ManyToOne(targetEntity = OfferImpl.class, optional=false)
    @JoinColumn(name = "OFFER_ID")
    @Index(name="OIADJUST_OFFER_INDEX", columnNames={"OFFER_ID"})
    protected Offer offer;

    @Column(name = "ADJUSTMENT_REASON", nullable=false)
    @AdminPresentation(friendlyName="Item Adjustment Reason", order=1, group="Description")
    protected String reason;

    @Column(name = "ADJUSTMENT_VALUE", nullable=false)
    @AdminPresentation(friendlyName="Item Adjustment Value", order=2, group="Description")
    protected BigDecimal value;

    public void init(OrderItem orderItem, Offer offer, String reason){
        this.orderItem = orderItem;
        this.offer = offer;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public Offer getOffer() {
        return offer;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Money getValue() {
        return value == null ? null : new Money(value);
    }
    
    public void setValue(Money value) {
    	this.value = value.getAmount();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((offer == null) ? 0 : offer.hashCode());
        result = prime * result + ((orderItem == null) ? 0 : orderItem.hashCode());
        result = prime * result + ((reason == null) ? 0 : reason.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItemAdjustmentImpl other = (OrderItemAdjustmentImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (offer == null) {
            if (other.offer != null)
                return false;
        } else if (!offer.equals(other.offer))
            return false;
        if (orderItem == null) {
            if (other.orderItem != null)
                return false;
        } else if (!orderItem.equals(other.orderItem))
            return false;
        if (reason == null) {
            if (other.reason != null)
                return false;
        } else if (!reason.equals(other.reason))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}