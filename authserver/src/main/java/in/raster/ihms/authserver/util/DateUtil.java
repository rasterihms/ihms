/*******************************************************************************
 *  CONFIDENTIAL AND PROPRIETARY
 *
 *  The source code and other information contained herein is the confidential and the exclusive property of
 *  Raster Images Pvt. Ltd. and is subject to the terms and conditions in your end user license agreement.
 *  This source code, and any other information contained herein, shall not be copied, reproduced, published,
 *  displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
 *  expressly permitted under such license agreement.
 *
 *  Copyright Raster Images Pvt. Ltd.
 *
 *  ALL RIGHTS RESERVED
 *******************************************************************************/
package in.raster.ihms.authserver.util;

import org.apache.directory.api.util.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Date util to manipulate date object.
 */
public class DateUtil {

    /**
     * Converting date to Instant.
     *
     * @param date - Date that has to be converted to Instant
     * @return Instant for the date object.
     */
    public static Instant convertDateToInstant(final Date date) {
        final ZoneOffset zoneOffset = ZonedDateTime.now().getOffset();
        return date.toInstant().plus(zoneOffset.getTotalSeconds(), ChronoUnit.SECONDS);
    }

    /**
     * Converts the ldap time stamp to java instant.
     *
     * @param ldapTimeStamp - generalized time.
     * @return Instant value
     */
    public static Instant convertLdapTimeStampToInstant(final String ldapTimeStamp) {
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuuMMddHHmmss[.SSS]X");
        final OffsetDateTime odt = OffsetDateTime.parse(ldapTimeStamp, format);
        return odt.toInstant();
    }

    /**
     * Converts instant to ldap time stamp.
     *
     * @param inputTimeStamp - instant time stamp.
     * @return ldap time stamp
     */
    public static String convertInstantToLdapTimeStamp(final Instant inputTimeStamp) {
        final Date dateObj = Date.from(inputTimeStamp);
        return DateUtils.getGeneralizedTime(dateObj);
    }
}
