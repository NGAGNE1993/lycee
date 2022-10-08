import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { IFiliere } from 'app/entities/filiere/filiere.model';
import { NiveauQualif } from 'app/entities/enumerations/niveau-qualif.model';

export interface INiveauxCalification {
  id?: number;
  niveauCalification?: string;
  typeNiveauCalification?: NiveauQualif | null;
  lyceesTechniques?: ILyceesTechniques | null;
  directeur?: IDirecteurEtude | null;
  filiere?: IFiliere | null;
}

export class NiveauxCalification implements INiveauxCalification {
  constructor(
    public id?: number,
    public niveauCalification?: string,
    public typeNiveauCalification?: NiveauQualif | null,
    public lyceesTechniques?: ILyceesTechniques | null,
    public directeur?: IDirecteurEtude | null,
    public filiere?: IFiliere | null
  ) {}
}

export function getNiveauxCalificationIdentifier(niveauxCalification: INiveauxCalification): number | undefined {
  return niveauxCalification.id;
}
