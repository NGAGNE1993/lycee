import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { IProviseur } from 'app/entities/proviseur/proviseur.model';
import { Provenance } from 'app/entities/enumerations/provenance.model';
import { NaturePart } from 'app/entities/enumerations/nature-part.model';

export interface IPartenaire {
  id?: number;
  denominationPartenaire?: string;
  statauPartenaire?: Provenance;
  autreCategorie?: string | null;
  typeAppui?: NaturePart;
  autreNature?: string | null;
  lyceesTechniques?: ILyceesTechniques | null;
  proviseur?: IProviseur | null;
}

export class Partenaire implements IPartenaire {
  constructor(
    public id?: number,
    public denominationPartenaire?: string,
    public statauPartenaire?: Provenance,
    public autreCategorie?: string | null,
    public typeAppui?: NaturePart,
    public autreNature?: string | null,
    public lyceesTechniques?: ILyceesTechniques | null,
    public proviseur?: IProviseur | null
  ) {}
}

export function getPartenaireIdentifier(partenaire: IPartenaire): number | undefined {
  return partenaire.id;
}
