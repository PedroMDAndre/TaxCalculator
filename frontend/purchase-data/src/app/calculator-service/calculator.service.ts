import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PurchaseDataDto } from '../model/purchase-data-dto';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {

  private url: string = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  calculatePurchaseData(purchaseDataDto: PurchaseDataDto): Observable<PurchaseDataDto> {
    return this.http.post<PurchaseDataDto>(this.url + '/calculate', purchaseDataDto)
  }
}
