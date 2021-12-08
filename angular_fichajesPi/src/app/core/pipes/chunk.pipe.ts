import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: 'chunk'
})
export class ChunkPipe implements PipeTransform {

  //El filtro recorre los días del mes y crea un array de bloques de 7 dias
  transform(calendarDaysArray: any, chunkSize: number): any {
    //array que contendrá los bloques de 7 días
    let calendarDays: any[] = [];
    //bloques semanales
    let weekDays: any[] = [];

    //recorremos los días del mes y los metemos en el bloque de semana hasta que el resto de dividirlo entre chunkSize es 0 entonces guardamos el bloque y lo limpiamos
    calendarDaysArray.map((day: any, index: number) => {
      weekDays.push(day);
      if (++index % chunkSize === 0) {
        calendarDays.push(weekDays);
        weekDays = [];
      }
    });
    return calendarDays;
  }
}