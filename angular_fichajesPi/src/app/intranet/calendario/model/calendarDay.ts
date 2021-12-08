
export class CalendarDay {
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

}