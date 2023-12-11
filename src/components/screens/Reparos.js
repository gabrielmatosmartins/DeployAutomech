import '../../App.css';
import { TableResultReparos } from '../../components/tableResultReparos/tableResult';
import '../../css/search.css'
import '../../css/style.css'

import { FuncionarioValidado } from "../../components/EntidadesValidadas/funcionarioValidado"

export function RepairScreen() {
  return (
    
    <div className='normalBackground'>
      <FuncionarioValidado/>
      <TableResultReparos></TableResultReparos>
    </div>
  );
}