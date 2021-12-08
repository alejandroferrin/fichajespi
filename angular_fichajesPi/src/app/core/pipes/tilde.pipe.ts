import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: 'sin-tilde'
})
export class TildePipe implements PipeTransform {

  filtrar_acentos(input: string) {
    var acentos = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇç";
    var original = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunncc";
    for (var i = 0; i < acentos.length; i++) {
      input = input.replace(acentos.charAt(i), original.charAt(i));
    };
    return input;
  }

  //El filtro recorre los días del mes y crea un array de bloques de 7 dias
  transform(input: string): any {
    return this.filtrar_acentos(input);
  }
}