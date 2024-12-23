/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe astratta per le funzioni di utilita' sulle date. La classe r' abstract
 * perche' non deve essere usata direttamente ma solo dalla sua implementazione
 * nella costante Utils.DATE
 * 
 * @author Stefano Einaudi (Matr. 70399)
 * 
 */

public abstract class DateUtils
{
  public final static String          DATE_TIME_FORMAT_STRING = "dd/MM/yyyy HH:mm:ss";
  public final static String          DATE_FORMAT_STRING      = "dd/MM/yyyy";
  public final static String          DATE_FORMAT_STRING_UNDERSCORE      = "dd_MM_yyyy";
  public final static SafeDateFormat  DATE_FORMAT             = new SafeDateFormat(DATE_FORMAT_STRING);
  private static final SafeDateFormat DATE_TIME_FORMAT        = new SafeDateFormat(DATE_TIME_FORMAT_STRING);
  public final static SafeDateFormat  DATE_FORMAT_UNDERSCORE  = new SafeDateFormat(DATE_FORMAT_STRING_UNDERSCORE);

  public String formatDateTime(Date date)
  {
    try
    {
      return DATE_TIME_FORMAT.format(date);
    }
    catch (Exception e)
    {
      return "";
    }
  }

  public String formatDate(Date date)
  {
    try
    {
      return DATE_FORMAT.format(date);
    }
    catch (Exception e)
    {
      return "";
    }
  }
  
  public String formatDateUnderscore(Date date)
  {
    try
    {
      return DATE_FORMAT_UNDERSCORE.format(date);
    }
    catch (Exception e)
    {
      return "";
    }
  }

  public Date parseDateTime(String date)
  {
    return parseDateTime(date, false);
  }

  public Date parseDateTime(String date, boolean timeIsOptional)
  {
    try
    {
      return DATE_TIME_FORMAT.parse(date);
    }
    catch (ParseException ex1)
    {
      // Non e' una data in formato DATE_TIME_FORMAT_STRING
      if (timeIsOptional)
      {
        // Ma se l'ora e' opzionale potrebbe essere una data in formato
        // DATE_FORMAT_STRING
        try
        {
          return DATE_FORMAT.parse(date);
          // Infatti lo e', ritorno l'oggetto date corrispondente
        }
        catch (ParseException ex2)
        {
          // Non lo e', ritorno null alla fine metedo
        }
      }
    }
    catch (Exception e)
    {
      // Ritorno null alla fine metedo
    }
    return null;

  }

  public Date parseDate(String date)
  {
    try
    {
      date = date.trim();
      /*
       * Purtroppo devo fare il controllo sulla lunghezza a causa di una
       * gestione "strana" nella parsificazione effettuata dalla
       * SimpleDateFormat (con un numero di y > 2 nel pattern non controlla piu'
       * la coerenza tra numero di y e numero di cifre dell'anno, ma accetta
       * qualsiasi cosa, quindi con un pattern con yyyy non da errore se
       * inserisco 100000 come anno)
       */
      if (date.length() == 10)
      {
        return DATE_FORMAT.parse(date);
      }
      else
      {
        return null;
      }
    }
    catch (ParseException ex2)
    {
      return null;
    }
    catch (Exception e)
    {
      return null;
    }
  }

  public Date setTimeOfTheDate(Date date, int hh24, int mm, int ss, int ms)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, hh24);
    calendar.set(Calendar.MINUTE, mm);
    calendar.set(Calendar.SECOND, ss);
    calendar.set(Calendar.MILLISECOND, ms);
    return calendar.getTime();
  }

  public Date addDay(Date date, int day)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_YEAR, day);
    return calendar.getTime();
  }

  public Date getCurrentDateNoTime()
  {
    Date today = new Date();
    today = setTimeOfTheDate(today, 0, 0, 0, 0);
    return today;
  }
  
  public static int getCurrentYear() {
      final Calendar cal = new GregorianCalendar();
      return cal.get(Calendar.YEAR);
  }

  public Date removeTime(Date date)
  {
    return setTimeOfTheDate(date, 0, 0, 0, 0);
  }

  public Integer getYearFromDate(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.YEAR);
  }

  public long diffInSeconds(Date first, Date second)
  {
    return (first.getTime() - second.getTime()) / 1000;
  }

  public Date fromXMLGregorianCalendar(XMLGregorianCalendar calendar)
  {
    if (calendar == null)
    {
      return null;
    }
    return calendar.toGregorianCalendar().getTime();
  }

  public XMLGregorianCalendar toXMLGregorianCalendar(Date date)
  {
    if (date == null)
    {
      return null;
    }
    GregorianCalendar c = new GregorianCalendar();
    c.setTime(date);
    try
    {
      return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }
    catch (DatatypeConfigurationException e)
    {
      return null;
    }
  }
  
  
  
}

class SafeDateFormat extends ThreadLocal<DateFormat>
{
  private String format;

  public SafeDateFormat(String format)
  {
    this.format = format;
  }

  @Override
  public DateFormat get()
  {
    return super.get();
  }

  @Override
  protected DateFormat initialValue()
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    simpleDateFormat.setLenient(false);
    simpleDateFormat.setLenient(false);
    return simpleDateFormat;
  }

  @Override
  public void remove()
  {
    super.remove();
  }

  @Override
  public void set(DateFormat value)
  {
    super.set(value);
  }

  public String format(Date aDate)
  {
    return get().format(aDate);
  }

  public Date parse(String aDate) throws ParseException
  {
    return get().parse(aDate);
  }
  
  

};
