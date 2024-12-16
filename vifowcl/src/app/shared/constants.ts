/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class CONST {

    public static readonly IT = {
        firstDayOfWeek: 1,
        dayNames: [ "Domenica","Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato" ],
        dayNamesShort: [ "Dom","Lun","Mar","Mer","Gio","Ven","Sab"],
        dayNamesMin: [ "D","L","M","M","G","V","S" ],
        monthNames: [ "Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre" ],
        monthNamesShort: [ "Gen","Feb","Mar","Apr","Mag","Giu","Lug","Ago","Set","Ott","Nov","Dic" ],
        today: 'Oggi',
        clear: 'Annulla'
    }

    public static DOCS_TYPE ={
        DOMANDA_GRATUITA_NO_FIRMA: 1,
        DOMANDA_GRATUITA_FIRMA_DIGITALE: 2,
        DOMANDA_GRATUITA_FIRMA_AUTOGRAFA: 3,
        DOMANDA_ONEROSA_NO_FIRMA: 4,
        DOMANDA_ONEROSA_FIRMA_DIGITALE: 5,
        DOMANDA_ONEROSA_FIRMA_AUTOGRAFA: 6,
        ASSEGNAZIONE_GRATUITA_NO_FIRMA: 7,
        ASSEGNAZIONE_GRATUITA_FIRMA_DIGITALE: 8,
        ASSEGNAZIONE_GRATUITA_FIRMA_AUTOGRAFA: 9,
        ASSEGNAZIONE_ONEROSA_NO_FIRMA: 10,
        ASSEGNAZIONE_ONEROSA_FIRMA_DIGITALE: 11,
        ASSEGNAZIONE_ONEROSA_FIRMA_AUTOGRAFA: 12,
        SCANSIONE_DOCUMENTO_IDENTITA: 13
    }

    public static PDF_TYPE ={
        ASSEGNAZIONE_GRATUITA: 'ASSEGNAZIONE_GRATUITA',
        ASSEGNAZIONE_ONEROSA:'ASSEGNAZIONE_ONEROSA',
        CERTIFICATO:'CERTIFICATO',
        DDT:'DDT',
        DOMANDA_GRATUITA:'DOMANDA_GRATUITA',
        DOMANDA_ONEROSA:'DOMANDA_ONEROSA',
        ETICHETTA:'ETICHETTA',
        ETICHETTA_QR_CODE:'ETICHETTA_QR_CODE'
    }

    public static DOMANDA_TYPE = {
        DOMANDA_GRATUITA: 1,
        DOMANDA_ONEROSA: 2
    }

    public static readonly PATTERN_MAIL: string = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static readonly PATTERN_CAP: string = "\\d{5}";
    public static readonly PATTERN_NUMERIC: string = "^[1-9+]*$";
    public static readonly PATTERN_PARTITA_IVA = "^[0-9]{11}$";
    public static readonly PATTERN_NUMERIC_WITH_ZERO: string = "^[0-9]*$";
    public static readonly PATTERN_NUMERIC_WITH_2_DECIMAL = "^[0-9]*\,[0-9]{2}$";
    public static readonly PATTERN_NUMERIC_WITH_3_DECIMAL = "^[0-9]*\,?[0-9]{0,3}$";
    public static readonly PATTERN_PROVINCIA_ORDINE: string = "^[0-9]{3}$";
    public static readonly PATTERN_YEAR: string = "^((19|20|21)\\d{2})$";
    public static readonly PATTERN_MONETARY: string = "^(((\\d{1,3})(?:,[0-9]{3}){0,4}|(\\d{1,12}))(\\.[0-9]{1,2})?)?$";
    
    public static readonly PROFILE = {RICHIEDENTE:1, VIVAIO:2, GESTORE:3}

    public static readonly RICHIEDENTE_ROUTES: string[] = ["visualizza","form-domanda/:tipoDomanda","modifica/:idDomanda","domanda/:idDomanda"
        ,"riepilogo/assegnata/:idDomanda","riepilogo/preassegnata/:idDomanda","riepilogo/inviata/:idDomanda","riepilogo/rifiutata/:idDomanda",
        "riepilogo/chiusa/:idDomanda","riepilogo/ritiro/:idDomanda","riepilogo/chiusa/:idDomanda"];
    public static readonly VIVAIO_ROUTES: string[] = ["visualizza","form-domanda/:tipoDomanda",
        "form-assegnazione/:idDomanda","modifica/:idDomanda","domanda/:idDomanda","riepilogo/inviata/:idDomanda","riepilogo/rifiutata/:idDomanda",
        "riepilogo/assegnata/:idDomanda","riepilogo/preassegnata/:idDomanda","ricerca","inventari","anagrafiche",
        "riepilogo/ritiro/:idDomanda","form-ddt/:idDomanda","modifica-ddt/:idDomanda","dettaglio-ddt/:numeroDdt/:annoDdt",
        "form-ddt/:numeroDdt/:annoDdt","riepilogo/chiusa/:idDomanda","modifica-ddt/:numeroDdt/:annoDdt",
        "form-frutti","form-semi","form-piantine","form-ritiro/:idDomanda",
        "dettaglio-frutti/:idInventario/:edit","dettaglio-semi/:idInventario/:edit","dettaglio-piantine/:idInventario/:edit",
        "inventario-frutti","inventario-semi","inventario-piantine",
        "trasferimenti","dettaglio-trasferimento/:id/:edit","form-nuova-annata-inventario",
        "anagrafica-soggetti","form-soggetto/:id","anagrafica-specie","form-specie","form-specie/:id",
        "certificati","form-certificato","modifica-certificato/:id"];
    
        public static readonly GESTORE_ROUTES: string[] = ["ricerca","inventari","anagrafiche",
        "inventario-frutti","inventario-semi","inventario-piantine",
        "form-frutti","form-semi","form-piantine",
        "dettaglio-frutti/:idInventario/:edit","dettaglio-semi/:idInventario/:edit","dettaglio-piantine/:idInventario/:edit",
        "form-certificato","modifica-certificato/:id","form-ritiro/:idDomanda",
        "modifica/:idDomanda","domanda/:idDomanda","riepilogo/assegnata/:idDomanda",
        "riepilogo/preassegnata/:idDomanda","form-assegnazione/:idDomanda",
        "form-ritiro/:idDomanda","form-ddt/:idDomanda","modifica-ddt/:numeroDdt/:annoDdt","dettaglio-ddt/:numeroDdt/:annoDdt",
        "riepilogo/inviata/:idDomanda","riepilogo/ritiro/:idDomanda","riepilogo/rifiutata/:idDomanda",
        "form-ddt/:numeroDdt/:annoDdt","riepilogo/chiusa/:idDomanda",
        "trasferimenti","dettaglio-trasferimento/:id/:edit","form-nuova-annata-inventario",
        "anagrafica-soggetti","form-soggetto/:id","anagrafica-specie","form-specie","form-specie/:id",
        "certificati"];
    
    public static readonly TOOLTIP_CODICE_PROFESSIONISTA : string = "NOTA ALLA COMPILAZIONE: Nel caso di società il codice fiscale coincide con la partita IVA (11 caratteri numerici); nel caso di imprese individuali il codice fiscale coincide con il codice fiscale del legale rappresentante (16 caratteri alfanumerici)."
    public static readonly AUTOCOMPLETE_EMPTY_MESSAGE: string = "nessun risultato trovato"
    
    public static readonly STEP_FIVE_EXTERNAL_LINK = "https://www.example.com";
    
}
