package com.chatApp.chatapi.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Apache kullanımında object equality için gerekli reflectionlar
 * <p>
 * //______ !!!!!!!!! _______//
 * *** !! Eğer ana snıf(entity) birçok property içeriyorsa performans problemine yol açabilir, Bu durumda aşağıdaki kodlar kullanılabilir.
 *
 * @Override public boolean equals(Object o) {
 * if (!(o instanceof Admin)) { return false; }
 * Admin otherAdmin  = (Admin) o;
 * EqualsBuilder builder = new EqualsBuilder();
 * builder.append(getUserName(), otherAdmin.getUserName());
 * return builder.isEquals();
 * }
 * @Override public int hashCode() {
 * HashCodeBuilder builder = new HashCodeBuilder();
 * builder.append(getUserName());
 * return builder.hashCode();
 * }
 */
public class ValueObject {

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
