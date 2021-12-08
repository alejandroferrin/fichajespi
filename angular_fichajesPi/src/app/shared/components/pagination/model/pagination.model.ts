
export interface Pagination {
  totalPages: Array<number>
  page: number
  isFirst: boolean
  isLast: boolean
  size: number
  sizeLimit: number
  listPagesLimits: number
}