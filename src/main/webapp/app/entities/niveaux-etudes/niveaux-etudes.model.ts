import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { ISerie } from 'app/entities/serie/serie.model';
import { TypeNiveau } from 'app/entities/enumerations/type-niveau.model';

export interface INiveauxEtudes {
  id?: number;
  nomNiveau?: string;
  typeNiveau?: TypeNiveau | null;
  lyceesTechniques?: ILyceesTechniques | null;
  directeur?: IDirecteurEtude | null;
  serie?: ISerie | null;
}

export class NiveauxEtudes implements INiveauxEtudes {
  constructor(
    public id?: number,
    public nomNiveau?: string,
    public typeNiveau?: TypeNiveau | null,
    public lyceesTechniques?: ILyceesTechniques | null,
    public directeur?: IDirecteurEtude | null,
    public serie?: ISerie | null
  ) {}
}

export function getNiveauxEtudesIdentifier(niveauxEtudes: INiveauxEtudes): number | undefined {
  return niveauxEtudes.id;
}
