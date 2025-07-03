package com.example.nonloso

class GlobalVars {
    companion object {
        var btnClicked = false
        var btnNuovo = false
        var btnSceltaPremuto = false
        var nomeAccount = ""
        var tempo = 0
        var numMes = 0
        var modelloSelezionato = 0
        var percorsoFolder = ""
        var percorsoModel1Folder = ""
        var percorsoModel2Folder = ""
        var percorsoModel3Folder = ""
        var percorsoModel4Folder = ""
        var percorsoModel5Folder = ""
        var percorsoModel6Folder = ""
        var percorsoModel7Folder = ""
        var selezionato = 0


        var numeroConversazioni = 5

        fun random(creaNuova: Boolean): MutableMap<String, Int> { //se è su falso, ne crea una nuova, e dopodichè viene messa su vero, invece se vero, non restituisce nulla
            val date = mutableMapOf<String, Int>()
            if (!creaNuova){
                for (i in 1..numeroConversazioni) {
                    val Variabile = "bottoneConv$i"
                    date[Variabile] = 1
                }
                return date
            }else{
                return date
            }
        }
    }



}