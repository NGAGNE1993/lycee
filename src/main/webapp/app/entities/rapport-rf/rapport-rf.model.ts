import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { IProviseur } from 'app/entities/proviseur/proviseur.model';
import { TypeRapport } from 'app/entities/enumerations/type-rapport.model';

export interface IRapportRF {
  id?: number;
  typeRaport?: TypeRapport | null;
  rentreContentType?: string | null;
  rentre?: string | null;
  finContentType?: string | null;
  fin?: string | null;
  lyceesTechniques?: ILyceesTechniques | null;
  proviseur?: IProviseur | null;
}

export class RapportRF implements IRapportRF {
  constructor(
    public id?: number,
    public typeRaport?: TypeRapport | null,
    public rentreContentType?: string | null,
    public rentre?: string | null,
    public finContentType?: string | null,
    public fin?: string | null,
    public lyceesTechniques?: ILyceesTechniques | null,
    public proviseur?: IProviseur | null
  ) {}
}

export function getRapportRFIdentifier(rapportRF: IRapportRF): number | undefined {
  return rapportRF.id;
}
