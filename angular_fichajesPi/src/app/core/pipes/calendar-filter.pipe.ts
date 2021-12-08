import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'calendarFilter'
})
export class CalendarFilterPipe implements PipeTransform {

  transform(list: any[], filters: any) {

    const keys = Object.keys(filters).filter(key => filters[key]);

    const filterElement = (element: any) => keys.every(key => element[key] == filters[key]);

    return keys.length ? list.filter(filterElement) : list;

  }

}
