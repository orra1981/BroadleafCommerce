/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.openadmin.client.dto;

import org.broadleafcommerce.openadmin.client.dto.visitor.PersistencePerspectiveItemVisitor;

import java.io.Serializable;

/**
 * 
 * @author jfischer
 *
 */
public class MapStructure implements Serializable, PersistencePerspectiveItem {

    private static final long serialVersionUID = 1L;
    
    private String keyClassName;
    private String keyPropertyName;
    private String keyPropertyFriendlyName;
    private String valueClassName;
    private String mapProperty;
    private Boolean deleteValueEntity = Boolean.FALSE;
    
    public MapStructure() {
        //do nothing - support serialization requirements
    }
    
    public MapStructure(String keyClassName, String keyPropertyName, String keyPropertyFriendlyName, String valueClassName, String mapProperty, Boolean deleteValueEntity) {
        if (!keyClassName.equals(String.class.getName())) {
            throw new RuntimeException("keyClass of java.lang.String is currently the only type supported");
        }
        this.keyClassName = keyClassName;
        this.valueClassName = valueClassName;
        this.mapProperty = mapProperty;
        this.keyPropertyName = keyPropertyName;
        this.keyPropertyFriendlyName = keyPropertyFriendlyName;
        this.deleteValueEntity = deleteValueEntity;
    }
    
    public String getKeyClassName() {
        return keyClassName;
    }
    
    public void setKeyClassName(String keyClassName) {
        if (!keyClassName.equals(String.class.getName())) {
            throw new RuntimeException("keyClass of java.lang.String is currently the only type supported");
        }
        this.keyClassName = keyClassName;
    }
    
    public String getValueClassName() {
        return valueClassName;
    }
    
    public void setValueClassName(String valueClassName) {
        this.valueClassName = valueClassName;
    }
    
    public String getMapProperty() {
        return mapProperty;
    }
    
    public void setMapProperty(String mapProperty) {
        this.mapProperty = mapProperty;
    }

    public String getKeyPropertyName() {
        return keyPropertyName;
    }

    public void setKeyPropertyName(String keyPropertyName) {
        this.keyPropertyName = keyPropertyName;
    }

    public String getKeyPropertyFriendlyName() {
        return keyPropertyFriendlyName;
    }

    public void setKeyPropertyFriendlyName(String keyPropertyFriendlyName) {
        this.keyPropertyFriendlyName = keyPropertyFriendlyName;
    }

    public Boolean getDeleteValueEntity() {
        return deleteValueEntity;
    }

    public void setDeleteValueEntity(Boolean deleteValueEntity) {
        this.deleteValueEntity = deleteValueEntity;
    }

    public void accept(PersistencePerspectiveItemVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public PersistencePerspectiveItem clonePersistencePerspectiveItem() {
        MapStructure mapStructure = new MapStructure();
        mapStructure.keyClassName = keyClassName;
        mapStructure.keyPropertyName = keyPropertyName;
        mapStructure.keyPropertyFriendlyName = keyPropertyFriendlyName;
        mapStructure.valueClassName = valueClassName;
        mapStructure.mapProperty = mapProperty;
        mapStructure.deleteValueEntity = deleteValueEntity;

        return mapStructure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapStructure)) return false;

        MapStructure that = (MapStructure) o;

        if (deleteValueEntity != null ? !deleteValueEntity.equals(that.deleteValueEntity) : that.deleteValueEntity != null)
            return false;
        if (keyClassName != null ? !keyClassName.equals(that.keyClassName) : that.keyClassName != null) return false;
        if (keyPropertyFriendlyName != null ? !keyPropertyFriendlyName.equals(that.keyPropertyFriendlyName) : that.keyPropertyFriendlyName != null)
            return false;
        if (keyPropertyName != null ? !keyPropertyName.equals(that.keyPropertyName) : that.keyPropertyName != null)
            return false;
        if (mapProperty != null ? !mapProperty.equals(that.mapProperty) : that.mapProperty != null) return false;
        if (valueClassName != null ? !valueClassName.equals(that.valueClassName) : that.valueClassName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = keyClassName != null ? keyClassName.hashCode() : 0;
        result = 31 * result + (keyPropertyName != null ? keyPropertyName.hashCode() : 0);
        result = 31 * result + (keyPropertyFriendlyName != null ? keyPropertyFriendlyName.hashCode() : 0);
        result = 31 * result + (valueClassName != null ? valueClassName.hashCode() : 0);
        result = 31 * result + (mapProperty != null ? mapProperty.hashCode() : 0);
        result = 31 * result + (deleteValueEntity != null ? deleteValueEntity.hashCode() : 0);
        return result;
    }
}
