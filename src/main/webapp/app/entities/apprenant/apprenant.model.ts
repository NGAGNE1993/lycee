import dayjs from 'dayjs/esm';
import { ISerie } from 'app/entities/serie/serie.model';
import { IFiliere } from 'app/entities/filiere/filiere.model';
import { INiveauxEtudes } from 'app/entities/niveaux-etudes/niveaux-etudes.model';
import { INiveauxCalification } from 'app/entities/niveaux-calification/niveaux-calification.model';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { Genre } from 'app/entities/enumerations/genre.model';
import { Option } from 'app/entities/enumerations/option.model';
import { Situation } from 'app/entities/enumerations/situation.model';

export interface IApprenant {
  id?: number;
  nomPrenom?: string;
  dateNaissance?: dayjs.Dayjs;
  lieuNaissance?: string;
  telephone?: string;
  adresse?: string;
  sexe?: Genre;
  anneeScolaire?: dayjs.Dayjs;
  option?: Option | null;
  situationMatrimoniale?: Situation;
  tuteur?: string;
  contactTuteur?: string;
  serie?: ISerie | null;
  filiere?: IFiliere | null;
  niveauxEtudes?: INiveauxEtudes | null;
  niveauxCalification?: INiveauxCalification | null;
  lyceesTechniques?: ILyceesTechniques | null;
  directeur?: IDirecteurEtude | null;
}

export class Apprenant implements IApprenant {
  constructor(
    public id?: number,
    public nomPrenom?: string,
    public dateNaissance?: dayjs.Dayjs,
    public lieuNaissance?: string,
    public telephone?: string,
    public adresse?: string,
    public sexe?: Genre,
    public anneeScolaire?: dayjs.Dayjs,
    public option?: Option | null,
    public situationMatrimoniale?: Situation,
    public tuteur?: string,
    public contactTuteur?: string,
    public serie?: ISerie | null,
    public filiere?: IFiliere | null,
    public niveauxEtudes?: INiveauxEtudes | null,
    public niveauxCalification?: INiveauxCalification | null,
    public lyceesTechniques?: ILyceesTechniques | null,
    public directeur?: IDirecteurEtude | null
  ) {}
}

export function getApprenantIdentifier(apprenant: IApprenant): number | undefined {
  return apprenant.id;
}
