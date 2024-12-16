/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;



public class ErrorMessageDTO
{
  
  
  private String   code;
  private String   status;
  private String   title;
  
  
  public String getCode()
  {
    return code;
  }
  public void setCode(String code)
  {
    this.code = code;
  }
  public String getStatus()
  {
    return status;
  }
  public void setStatus(String status)
  {
    this.status = status;
  }
  public void setStatus(Integer status)
  {
    this.status = status.toString();
  }
  public String getTitle()
  {
    return title;
  }
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  
}
