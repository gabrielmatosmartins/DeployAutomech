import '../../App.css';
import { TableResultReparos } from '../tableResultReparos/tableResult';
import '../../css/search.css'
import '../../css/style.css'

import { ClienteValidado } from "../EntidadesValidadas/clienteValidado"

export function RepairScreen() {
  return (
    
    <div>
      <ClienteValidado/>
      <TableResultReparos></TableResultReparos>
    </div>
  );
}