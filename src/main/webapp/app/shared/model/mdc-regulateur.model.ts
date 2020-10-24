import { TypeCentre } from 'app/shared/model/enumerations/type-centre.model';
import { Etat } from 'app/shared/model/enumerations/etat.model';

export interface IMdcRegulateur {
  id?: number;
  nom?: string;
  prenom?: string;
  adresse?: string;
  telephone?: string;
  centre?: TypeCentre;
  etat?: Etat;
  estMobile?: boolean;
  estFixe?: boolean;
}

export class MdcRegulateur implements IMdcRegulateur {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public adresse?: string,
    public telephone?: string,
    public centre?: TypeCentre,
    public etat?: Etat,
    public estMobile?: boolean,
    public estFixe?: boolean
  ) {
    this.estMobile = this.estMobile || false;
    this.estFixe = this.estFixe || false;
  }
}
