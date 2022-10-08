import { IEnseignant } from 'app/entities/enseignant/enseignant.model';
import { INiveauxCalification } from 'app/entities/niveaux-calification/niveaux-calification.model';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { Filieres } from 'app/entities/enumerations/filieres.model';
import { SANTEBIOLOGIECHIMIE } from 'app/entities/enumerations/santebiologiechimie.model';
import { ELEVAGE } from 'app/entities/enumerations/elevage.model';

export interface IFiliere {
  id?: number;
  nomFiliere?: Filieres;
  nomAutre?: string | null;
  nomProgramme?: SANTEBIOLOGIECHIMIE | null;
  autreAGR?: string | null;
  nomProgrammeEl?: ELEVAGE | null;
  autreEL?: string | null;
  autreMIN?: string | null;
  autreBAT?: string | null;
  autreMECAN?: string | null;
  autreMENU?: string | null;
  autreAGRO?: string | null;
  autreELECTRO?: string | null;
  autreSTRUC?: string | null;
  autreEC?: string | null;
  autreCOM?: string | null;
  autreIN?: string | null;
  autreSAN?: string | null;
  autreProgramme?: string | null;
  enseignants?: IEnseignant[] | null;
  niveauCalifications?: INiveauxCalification[] | null;
  lyceesTechniques?: ILyceesTechniques | null;
  directeur?: IDirecteurEtude | null;
}

export class Filiere implements IFiliere {
  constructor(
    public id?: number,
    public nomFiliere?: Filieres,
    public nomAutre?: string | null,
    public nomProgramme?: SANTEBIOLOGIECHIMIE | null,
    public autreAGR?: string | null,
    public nomProgrammeEl?: ELEVAGE | null,
    public autreEL?: string | null,
    public autreMIN?: string | null,
    public autreBAT?: string | null,
    public autreMECAN?: string | null,
    public autreMENU?: string | null,
    public autreAGRO?: string | null,
    public autreELECTRO?: string | null,
    public autreSTRUC?: string | null,
    public autreEC?: string | null,
    public autreCOM?: string | null,
    public autreIN?: string | null,
    public autreSAN?: string | null,
    public autreProgramme?: string | null,
    public enseignants?: IEnseignant[] | null,
    public niveauCalifications?: INiveauxCalification[] | null,
    public lyceesTechniques?: ILyceesTechniques | null,
    public directeur?: IDirecteurEtude | null
  ) {}
}

export function getFiliereIdentifier(filiere: IFiliere): number | undefined {
  return filiere.id;
}
