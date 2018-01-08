<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates"%>
<t:layout>
    
   <!-- Posicao - Operacao - quantidade - observacao -->
        <section class="paines">
            <div class="divTipoNavio" id="atracados">
                <table>
                    <caption>
                        <span class="tituloTipoNavio">EMBARCAÇÕES ATRACADAS</span> 
                        <span class="paginacao">Página {{agenciamentosAtracadosNumberOfPages() == 0 ? 0 : agenciamentosAtracadosCurrentPage +1}} de {{ agenciamentosAtracadosNumberOfPages() }}</span>
                    </caption>
                    <thead>
                        <tr>
                            <th class="colEmbarcacao">EMBARCAÇÃO</th>
                            <th class="colViagem">VGM</th>
                            <th class="colAtracao">ATRACAÇÃO</th>
                            <th class="colProcedencia">ORIGEM</th>
                            <th class="colCalado">CALADO</th>
                            <th class="colLocalizacaoAtual no-phone no-phone-wide">POSIÇÃO</th>
                            <th class="colResumoCarga no-phone no-phone-wide">OPERAÇÃO</th>
                            <th class="colResumoQuantidade no-phone no-phone-wide">QUANTIDADE</th>
                            <th class="colObservacao no-phone no-phone-wide">OBSERVAÇÃO</th>
                            <th class="colDestino">DESTINO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="agenciamentoAtracado in agenciamentosAtracados | orderBy:'localizacaoAtual' | startFrom:agenciamentosAtracadosCurrentPage*agenciamentosAtracadosPageSize | limitTo:agenciamentosAtracadosPageSize"
                            ng-class="{vcp: agenciamentoAtracado.tipo=='VCP', pp: agenciamentoAtracado.tipo=='PP', ok: agenciamentoAtracado.tipo=='OK', anv: agenciamentoAtracado.tipo=='ANV'}">
                            <td>{{agenciamentoAtracado.nomeEmbarcacao}}</td>
                            <td>{{agenciamentoAtracado.vgm}}</td>
                            <td>{{agenciamentoAtracado.dataAtracacao}}</td>
                            <td>{{agenciamentoAtracado.portoOrigem}}</td>
                            <td>{{agenciamentoAtracado.caladoChegadaVante}} / {{agenciamentoAtracado.caladoChegadaRe}}</td> 
                            <td class="no-phone no-phone-wide">{{agenciamentoAtracado.localizacaoAtual}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoAtracado.resumoCarga}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoAtracado.resumoQuantidades}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoAtracado.observacao}}</td>
                            <td>{{agenciamentoAtracado.portoDestino}}</td>
                        </tr> 
                    </tbody>     
                </table>
            </div>
            <div class="divTipoNavio" id="fundeados">
                <table>
                    <caption>
                        <span class="tituloTipoNavio">EMBARCAÇÕES FUNDEADAS</span>
                        <span class="paginacao">Página {{agenciamentosFundeadosNumberOfPages() == 0 ? 0 : agenciamentosFundeadosCurrentPage +1}} de {{ agenciamentosFundeadosNumberOfPages() }}</span>
                    </caption>
                    <thead>
                        <tr>                            
                            <th class="colLp">LP</th>
                            <th class="colEmbarcacao">EMBARCAÇÃO</th>
                            <th class="colViagem">VGM</th>
                            <th class="colHoraOficial">CHEGADA</th>
                            <th class="colProcedencia">ORIGEM</th>
                            <th class="colCalado">CALADO</th>
                            <th class="colLocalizacaoAtual no-phone no-phone-wide">POSIÇÃO</th>
                            <th class="colResumoCarga no-phone no-phone-wide">OPERAÇÃO</th>
                            <th class="colResumoQuantidade no-phone no-phone-wide">QUANTIDADE</th>
                            <th class="colObservacao no-phone no-phone-wide">OBSERVAÇÃO</th>
                            <th class="colDestino">DESTINO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="agenciamentoFundeado in agenciamentosFundeados | orderBy:'dataHoraOficialChegada' | startFrom:agenciamentosFundeadosCurrentPage*agenciamentosFundeadosPageSize | limitTo:agenciamentosFundeadosPageSize"
                            ng-class="{vcp: agenciamentoFundeado.tipo=='VCP', pp: agenciamentoFundeado.tipo=='PP', ok: agenciamentoFundeado.tipo=='OK', anv: agenciamentoFundeado.tipo=='ANV'}">                            
                            <td>{{agenciamentoFundeado.tipo}}</td>
                            <td>{{agenciamentoFundeado.nomeEmbarcacao}}</td>
                            <td>{{agenciamentoFundeado.vgm}}</td>
                            <td>{{agenciamentoFundeado.dataHoraOficialChegada}}</td>
                            <td>{{agenciamentoFundeado.portoOrigem}}</td>
                            <td>{{agenciamentoFundeado.caladoChegadaVante}} / {{agenciamentoFundeado.caladoChegadaRe}}</td> 
                            <td class="no-phone no-phone-wide">{{agenciamentoFundeado.localizacaoAtual}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoFundeado.resumoCarga}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoFundeado.resumoQuantidades}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoFundeado.observacao}}</td>
                            <td>{{agenciamentoFundeado.portoDestino}}</td>
                        </tr> 
                    </tbody>     
                </table>
            </div>
            <div class="divTipoNavio" id="esperados">
                <table>
                    <caption>
                        <span class="tituloTipoNavio">EMBARCAÇÕES ESPERADAS</span>
                        <span class="paginacao">Página {{agenciamentosEsperadosNumberOfPages() == 0 ? 0 : agenciamentosEsperadosCurrentPage +1}} de {{ agenciamentosEsperadosNumberOfPages() }}</span>
                    </caption>
                    <thead>
                        <tr>
                            <th class="colLp">LP</th>
                            <th class="colEmbarcacao">EMBARCAÇÃO</th>
                            <th class="colViagem">VGM</th>
                            <th class="colEta">ETA</th>
                            <th class="colProcedencia">ORIGEM</th>
                            <th class="colCalado">CALADO</th>
                            <th class="colResumoCarga no-phone no-phone-wide">OPERAÇÃO</th>
                            <th class="colResumoQuantidade no-phone no-phone-wide">QUANTIDADE</th>
                            <th class="colObservacao no-phone no-phone-wide">OBSERVAÇÃO</th>
                            <th class="colDestino">DESTINO</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr ng-repeat="agenciamentoEsperado in agenciamentosEsperados | orderBy:'eta' | startFrom:agenciamentosEsperadosCurrentPage*agenciamentosEsperadosPageSize | limitTo:agenciamentosEsperadosPageSize"
                            ng-class="{vcp: agenciamentoEsperado.tipo=='VCP', pp: agenciamentoEsperado.tipo=='PP', ok: agenciamentoEsperado.tipo=='OK', anv: agenciamentoEsperado.tipo=='ANV'}">
                            <td>{{agenciamentoEsperado.tipo}}</td>
                            <td>{{agenciamentoEsperado.nomeEmbarcacao}}</td>
                            <td>{{agenciamentoEsperado.vgm}}</td>
                            <td>{{agenciamentoEsperado.eta}}</td>
                            <td>{{agenciamentoEsperado.portoOrigem}}</td>
                            <td>{{agenciamentoEsperado.caladoChegadaVante}} / {{agenciamentoEsperado.caladoChegadaRe}}</td> 
                            <td class="no-phone no-phone-wide">{{agenciamentoEsperado.resumoCarga}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoEsperado.resumoQuantidades}}</td>
                            <td class="no-phone no-phone-wide">{{agenciamentoEsperado.observacao}}</td>
                            <td>{{agenciamentoEsperado.portoDestino}}</td>
                        </tr> 
                    </tbody>     
                </table>
            </div>
            <div id="saidos-desviados" class="divTipoNavio">
                <div id="saidos" class="divNavioSaido">
                    <table>
                        <caption>
                            <span class="tituloTipoNavio">EMBARCAÇÕES SAÍDAS</span> 
                            <span class="paginacao">Página {{agenciamentosSaidosNumberOfPages() == 0 ? 0 : agenciamentosSaidosCurrentPage +1}} de {{ agenciamentosSaidosNumberOfPages() }}</span>
                        </caption>
                        <thead>
                            <tr>
                                <th class="colEmbarcacao">EMBARCAÇÃO</th>
                                <th class="colViagem">VIAGEM</th>
                                <th class="colSaida">SAÍDA</th>
                                <th class="colEtaSaidos">ETA DESTINO</th>
                                <th class="colDestino">DESTINO</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr ng-repeat="agenciamentoSaido in agenciamentosSaidos | startFrom:agenciamentosSaidosCurrentPage*agenciamentosSaidosPageSize | limitTo:agenciamentosSaidosPageSize"
                                ng-class="{vcp: agenciamentoSaido.tipo=='VCP', pp: agenciamentoSaido.tipo=='PP', ok: agenciamentoSaido.tipo=='OK', anv: agenciamentoSaido.tipo=='ANV'}">
                                <td>{{agenciamentoSaido.nomeEmbarcacao}}</td>
                                <td>{{agenciamentoSaido.vgm}}</td>
                                <td>{{agenciamentoSaido.dataSaida}}</td>
                                <td>{{agenciamentoSaido.etaProxPorto}}</td>
                                <td>{{agenciamentoSaido.portoDestino}}</td>
                            </tr> 
                        </tbody>     
                    </table>
                </div>
                <div id="desviados" class="divNavioDesviado">
                    <table>
                        <caption>
                            <span class="tituloTipoNavio">EMBARCAÇÕES DESVIADAS</span>
                            <span class="paginacao">Página {{agenciamentosDesviadosNumberOfPages() == 0 ? 0 : agenciamentosDesviadosCurrentPage +1}} de {{ agenciamentosDesviadosNumberOfPages() }}</span></caption>
                        <thead>
                            <tr>
                                <th class="colEmbarcacao">EMBARCAÇÃO</th>
                                <th class="colViagem">VIAGEM</th>
                                <th class="colSaida">SAÍDA</th>
                                <th class="colEtaSaidos">ETA DESTINO</th>
                                <th class="colDestino">DESTINO ANT</th>
                                <th class="colDestino">NOVO DESTINO</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr ng-repeat="agenciamentoDesviado in agenciamentosDesviados | startFrom:agenciamentosDesviadosCurrentPage*agenciamentosDesviadosPageSize | limitTo:agenciamentosDesviadosPageSize"
                                ng-class="{vcp: agenciamentoDesviado.tipo=='VCP', pp: agenciamentoDesviado.tipo=='PP', ok: agenciamentoDesviado.tipo=='OK', anv: agenciamentoDesviado.tipo=='ANV'}">
                                <td>{{agenciamentoDesviado.nomeEmbarcacao}}</td>
                                <td>{{agenciamentoDesviado.vgm}}</td>
                                <td>{{agenciamentoDesviado.dataSaida}}</td>
                                <td>{{agenciamentoDesviado.etaProxPorto}}</td>
                                <td>{{agenciamentoDesviado.destinoAnterior}}</td>
                                <td>{{agenciamentoDesviado.portoDestino}}</td>
                            </tr> 
                        </tbody>     
                    </table>
                </div>
            </div>
        </section>
                            
</t:layout>
