import { Component, Input, OnChanges, OnInit, Output, SimpleChanges, EventEmitter } from '@angular/core';
import { CalendarDay } from '../../model/calendarDay';
import { DiaDto } from '../../model/diaDto';

/* export class CalendarDay {
  public date: Date;
  public title: string = '';
  public isThisMonth: boolean;
  public isWeekend: boolean;
  public selected: boolean;

  public getDateString() {
    //return this.date.toISOString().split("T")[0]
    return this.formatDate(this.date)
  }

  constructor(d: Date, dateRef: Date) {
    this.date = d;
    this.isThisMonth = d.getMonth() === dateRef.getMonth()
    this.isWeekend = d.getDay() === 0 || d.getDay() === 6
    this.selected = false

  }

  private formatDate(d: Date): string {
    var mm = d.getMonth() + 1; // getMonth() is zero-based
    var dd = d.getDate();

    return [d.getFullYear(),
    (mm > 9 ? '' : '0') + mm,
    (dd > 9 ? '' : '0') + dd
    ].join('-');
  };

} */

/* @Pipe({
  name: 'chunk'
})
export class ChunkPipe implements PipeTransform {

  transform(calendarDaysArray: any, chunkSize: number): any {
    let calendarDays: any[] = [];
    let weekDays: any[] = [];

    calendarDaysArray.map((day: any, index: number) => {
      weekDays.push(day);
      if (++index % chunkSize === 0) {
        calendarDays.push(weekDays);
        weekDays = [];
      }
    });
    return calendarDays;
  }
} */

@Component({
  selector: 'app-month',
  templateUrl: './month.component.html',
  styleUrls: ['./month.component.css']
})

export class MonthComponent implements OnInit, OnChanges {

  public calendar: CalendarDay[] = [];

  public monthNames = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
  ];
  public displayMonth: string = '';

  @Input() year: number = 0;
  @Input() month: number = 0;
  @Input() horaInicio: string = ''
  @Input() horaFin: string = ''
  @Input() calendarioNombre = ''
  @Output() added = new EventEmitter<DiaDto>()

  public addDay(d: CalendarDay): void {
    if (d.isThisMonth) {
      this.calendar.map(c => {
        if (c.date === d.date) {
          c.selected = !c.selected
        }
        return c
      })

      this.added.emit(new DiaDto(
        d.getDateString(),
        this.horaInicio,
        this.horaFin,
        this.calendarioNombre
      ))
    }
  }

  ngOnInit(): void {
    this.generateCalendarDays();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.year = changes.year.currentValue
    this.generateCalendarDays()
  }

  private generateCalendarDays(): void {
    // we reset our calendar
    this.calendar = [];

    // we set the date 
    //let day: Date = new Date(new Date().setMonth(new Date().getMonth() + monthIndex));
    let day: Date = new Date(this.year, this.month - 1);

    // set the dispaly month for UI
    this.displayMonth = this.monthNames[day.getMonth()];

    let startingDateOfCalendar = this.getStartDateForCalendar(day);

    let dateToAdd = startingDateOfCalendar;

    for (var i = 0; i < 42; i++) {
      this.calendar.push(new CalendarDay(new Date(dateToAdd), new Date(this.year, this.month - 1)))
      dateToAdd = new Date(dateToAdd.setDate(dateToAdd.getDate() + 1));
    }
  }

  private getStartDateForCalendar(selectedDate: Date) {
    // for the day we selected let's get the previous month last day
    let lastDayOfPreviousMonth = new Date(selectedDate.setDate(0));
    // start by setting the starting date of the calendar same as the last day of previous month
    let startingDateOfCalendar: Date = lastDayOfPreviousMonth;

    // but since we actually want to find the last Monday of previous month
    // we will start going back in days intil we encounter our last Monday of previous month
    if (startingDateOfCalendar.getDay() != 1) {
      do {
        startingDateOfCalendar = new Date(startingDateOfCalendar.setDate(startingDateOfCalendar.getDate() - 1));
      } while (startingDateOfCalendar.getDay() != 1);
    }

    return startingDateOfCalendar;
  }


}
